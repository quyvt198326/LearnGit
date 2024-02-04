package com.example.lesson107.data.source

interface CountryDataSource {
    suspend fun loadData(callback: DataSourceCallback)

    interface DataSourceCallback{
        fun onCompleted(result: ResponseResult)
    }
}