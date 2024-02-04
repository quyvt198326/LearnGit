package com.example.lesson107.data.source.remote

import com.example.lesson107.data.model.Country
import com.example.lesson107.data.source.CountryDataSource
import com.example.lesson107.data.source.ResponseResult
import com.example.lesson107.utils.CountryUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.lang.Exception

class RemoteDataSource: CountryDataSource {
    override suspend fun loadData(callback: CountryDataSource.DataSourceCallback) {
        val service = createRetrofitObject()
        val countryListCall = service.countryData
        countryListCall.enqueue(object : Callback<List<Country>>{
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.body() != null){
                    callback.onCompleted(ResponseResult.Success(response.body()))
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                callback.onCompleted(ResponseResult.Error(Exception(t.message)))
            }

        })
    }

    private fun createRetrofitObject(): CountryService{
        val retrofit = Retrofit.Builder()
            .baseUrl(CountryUtils.BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
        return retrofit.create(CountryService::class.java)
    }
}