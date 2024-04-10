package com.example.weatherforecast.data.remote.dto

import android.util.Log
import com.example.weatherforecast.domain.model.Forecast
import com.squareup.moshi.Json

data class ForecastDto(
    val date: String,
    @field:Json(name = "date_ts")
    val dateTs: Long,
    val hours: List<HourDto>,
    val parts: PartsDto
)

fun ForecastDto.toForecast(): Forecast {
    val perHours = hours.map { it.toForecastPerHour() }

    Log.d("ForecastDtoError", this.toString())

    return Forecast(
        date = date,
        dateTs = dateTs,
        perHours = perHours,
        condition = parts.dayShort.condition,
        temp = parts.dayShort.temp,
        tempMin = parts.dayShort.tempMin,
        windSpeed = parts.dayShort.windSpeed,
    )
}