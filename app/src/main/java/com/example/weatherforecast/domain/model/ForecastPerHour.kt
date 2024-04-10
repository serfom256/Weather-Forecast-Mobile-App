package com.example.weatherforecast.domain.model

import com.example.weatherforecast.domain.extensions.conditionToEmoji

data class ForecastPerHour(
    val condition: String,
    val hour: String,
    val temp: Int,
    val windSpeed: Double
)

fun ForecastPerHour.toListString(): List<String> {
    return listOf("$hour:00", condition.conditionToEmoji(), windSpeed.toString()+"м/с", "$temp°C")
}