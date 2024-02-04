package com.example.lesson107.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey
    @ColumnInfo("country_id")
    @JsonProperty("id")
    val countryId: Int = 0,

    @ColumnInfo("country_name")
    @JsonProperty("name")
    val countryName: String = "",

    @ColumnInfo("country_capital")
    @JsonProperty("capital")
    val capital: String = "",

    @ColumnInfo("country_flag")
    @JsonProperty("flag")
    val flag: String = "",

    @ColumnInfo("country_population")
    @JsonProperty("population")
    val population: Float = 0f,

    @ColumnInfo("country_area")
    @JsonProperty("area")
    val area: Int = 0,

    @ColumnInfo("country_density")
    @JsonProperty("density")
    val density: Int = 0,

    @ColumnInfo("country_world_share")
    @JsonProperty("world_share")
    val worldShare: String = ""
)
