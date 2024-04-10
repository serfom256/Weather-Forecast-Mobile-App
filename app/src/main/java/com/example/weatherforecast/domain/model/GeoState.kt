package com.example.weatherforecast.domain.model

data class GeoState(
    val isLoading: Boolean = false,
    val geo: Geo? = null,
    val error: String = ""
)
