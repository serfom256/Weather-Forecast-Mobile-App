package com.example.weatherforecast.data.remote.dto

import com.squareup.moshi.Json

data class PartsDto(
    @field:Json(name = "day_short")
    val dayShort: DayShortDto
)