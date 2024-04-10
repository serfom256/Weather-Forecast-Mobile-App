package com.example.weatherforecast.domain.model

import com.example.weatherforecast.domain.extensions.conditionToEmoji
import com.example.weatherforecast.domain.extensions.enDayToRus
import java.text.SimpleDateFormat
import java.util.Locale

data class Forecast(
    val date: String,
    val dateTs: Long,
    val perHours: List<ForecastPerHour>,
    val condition: String,
    val temp: Int,
    val tempMin: Int,
    val windSpeed: Double
)

fun Forecast.toListString(): List<String> {
    return listOf(getDayOfWeek(dateTs).enDayToRus(), condition.conditionToEmoji(), windSpeed.toString()+"м/с", "$temp°C / $tempMin°C")
}

fun getDayOfWeek(timestamp: Long): String = SimpleDateFormat("EE", Locale.ENGLISH).format(timestamp * 1000)