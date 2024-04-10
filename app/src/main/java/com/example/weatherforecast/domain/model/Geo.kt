package com.example.weatherforecast.domain.model

import com.example.weatherforecast.common.Constants.DEFAULT_COORDINATE

data class Geo(
    val latitude: Double = DEFAULT_COORDINATE,
    val longitude: Double = DEFAULT_COORDINATE
)