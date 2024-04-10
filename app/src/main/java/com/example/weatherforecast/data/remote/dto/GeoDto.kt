package com.example.weatherforecast.data.remote.dto

import com.example.weatherforecast.domain.model.Geo

data class GeoDto(
    val lat: Double,
    val lon: Double
)

fun GeoDto.toGeo(): Geo {
    return Geo(
        latitude = lat,
        longitude = lon
    )
}