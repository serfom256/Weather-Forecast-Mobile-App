package com.example.weatherforecast.domain.model

data class ForecastState(
    val isLoading: Boolean = false,
    val forecasts: List<Forecast> = emptyList(),
    val error: String = ""
)
