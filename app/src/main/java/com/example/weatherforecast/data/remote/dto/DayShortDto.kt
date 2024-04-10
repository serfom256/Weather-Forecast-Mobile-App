package com.example.weatherforecast.data.remote.dto

import com.squareup.moshi.Json

data class DayShortDto(
    val condition: String,
    val temp: Int,
    @field:Json(name = "temp_min")
    val tempMin: Int,
    @field:Json(name = "wind_speed")
    val windSpeed: Double
)