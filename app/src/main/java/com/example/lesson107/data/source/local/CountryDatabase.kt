package com.example.lesson107.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lesson107.data.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase : RoomDatabase() {
    abstract val countryDao: CountryDao

    companion object {
        private var _instance: CountryDatabase? = null

        @JvmStatic
        fun instance(context: Context): CountryDatabase? {
            if (_instance == null) {
                _instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryDatabase::class.java,
                    "country_db.db"
                ).build()
            }
            return _instance
        }
    }
}