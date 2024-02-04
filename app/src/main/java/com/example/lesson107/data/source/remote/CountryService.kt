package com.example.lesson107.data.source.remote

import com.example.lesson107.data.model.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {
    @get:GET("resources/braniumapis/country.json")
    val countryData: Call<List<Country>>
}