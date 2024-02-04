package com.example.lesson107.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lesson107.data.source.CountryDataRepository

class CountryViewModelFactory(
    private val repository: CountryDataRepository
): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)){
            return CountryViewModel(repository) as T
        }else{
            throw IllegalArgumentException("Argument is not class CountryViewModel")
        }
    }
}