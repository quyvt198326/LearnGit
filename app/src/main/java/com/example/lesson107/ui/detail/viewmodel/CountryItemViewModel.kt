package com.example.lesson107.ui.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson107.data.model.Country

class CountryItemViewModel: ViewModel() {
    private val _country = MutableLiveData<Country>()
    val country: LiveData<Country> = _country

    fun updateSelectedCountry(country: Country){
        _country.value = country
    }
}