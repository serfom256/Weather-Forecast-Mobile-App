package com.example.weatherforecast.data.remote.dto

import com.example.weatherforecast.domain.model.ForecastPerHour
import com.squareup.moshi.Json

data class HourDto(
    val condition: String,
    val hour: String,
    @field:Json(name = "hour_ts")
    val hourTs: Int,
    val temp: Int,
    @field:Json(name = "wind_speed")
    val windSpeed: Double
)

fun HourDto.toForecastPerHour(): ForecastPerHour {
    return ForecastPerHour(
        condition = condition,
        hour = hour,
        temp = temp,
        windSpeed = windSpeed
    )
}