package com.example.lesson107.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson107.data.model.Country
import com.example.lesson107.data.source.CountryDataRepository
import com.example.lesson107.data.source.CountryDataSource
import com.example.lesson107.data.source.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(
    private val repository: CountryDataRepository
): ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> = _countries

    init {
        loadData()
    }

    @Suppress("unchecked_cast")
    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val callback = object : CountryDataSource.DataSourceCallback{
                override fun onCompleted(result: ResponseResult) {
                    if (result is ResponseResult.Success<*>){
                        val data = (result as ResponseResult.Success<List<Country>>).data
                        _countries.value = data
                    }else{
                        _countries.value = emptyList()
                    }
                }
            }
            repository.loadData(callback)
        }
    }

    fun cacheData(){
        viewModelScope.launch(Dispatchers.IO) {
            if (!_countries.value.isNullOrEmpty()){
                repository.cacheData(_countries.value!!)
            }
        }
    }
}