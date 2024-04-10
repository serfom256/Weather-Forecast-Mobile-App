package com.example.weatherforecast.domain.repository

import com.example.weatherforecast.data.remote.dto.GeoDto
import com.example.weatherforecast.data.remote.dto.WeatherTotalDto
import retrofit2.Response

interface WeatherRepository {
    suspend fun getForecast(latitude: String, longitude: String): Response<WeatherTotalDto>
    suspend fun getGeo(cityName: String, apiKey: String): Response<List<GeoDto>>
}