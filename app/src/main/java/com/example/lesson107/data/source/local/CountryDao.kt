package com.example.lesson107.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lesson107.data.model.Country

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg countries: Country)

    @Query("DELETE FROM countries")
    suspend fun clearAll()

    @Query("SELECT * FROM countries")
    suspend fun all(): List<Country>
}