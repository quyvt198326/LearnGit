package com.example.lesson107.data.source

import com.example.lesson107.data.model.Country
import com.example.lesson107.data.source.local.LocalDataSource
import com.example.lesson107.data.source.remote.RemoteDataSource

class CountryDataRepository(builder: Builder) {
    private val selectedDataSource: SelectedDataSource
    private val localDataSource: LocalDataSource
    private val remoteDataSource: RemoteDataSource

    init {
        selectedDataSource = builder.selectedDataSource
        localDataSource = builder.localDataSource
        remoteDataSource = builder.remoteDataSource
    }

    suspend fun loadData(callback: CountryDataSource.DataSourceCallback) {
        if (selectedDataSource == SelectedDataSource.LOCAL) {
            localDataSource.loadData(callback)
        } else {
            remoteDataSource.loadData(callback)
        }
    }

    suspend fun cacheData(data: List<Country>){
        localDataSource.clearDatabase()
        localDataSource.updateDatabase(data)
    }

    class Builder {
        lateinit var selectedDataSource: SelectedDataSource
        lateinit var localDataSource: LocalDataSource
        lateinit var remoteDataSource: RemoteDataSource

        fun setLocalDataSource(localDataSource: LocalDataSource): Builder {
            this.localDataSource = localDataSource
            return this
        }

        fun setRemoteDataSource(remoteDataSource: RemoteDataSource): Builder {
            this.remoteDataSource = remoteDataSource
            return this
        }

        fun setSelectedDataSource(selectedDataSource: SelectedDataSource): Builder {
            this.selectedDataSource = selectedDataSource
            return this
        }

        fun build(): CountryDataRepository {
            return CountryDataRepository(this)
        }
    }
}