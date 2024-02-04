package com.example.lesson107.data.source.local

import com.example.lesson107.data.model.Country
import com.example.lesson107.data.source.CountryDataSource
import com.example.lesson107.data.source.ResponseResult

class LocalDataSource(private val database: CountryDatabase?): CountryDataSource {
    override suspend fun loadData(callback: CountryDataSource.DataSourceCallback) {
        val data = database?.countryDao?.all()
        val result = ResponseResult.Success(data)
        callback.onCompleted(result)
    }

    suspend fun clearDatabase(){
        database?.countryDao?.clearAll()
    }

    suspend fun updateDatabase(countries: List<Country>){
        for (country in countries){
            database?.countryDao?.insert(country)
        }
    }
}