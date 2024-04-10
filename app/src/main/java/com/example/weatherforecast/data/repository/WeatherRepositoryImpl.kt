package com.example.weatherforecast.data.repository

import com.example.weatherforecast.data.remote.ApiServiceOWeather
import com.example.weatherforecast.data.remote.ApiServiceYa
import com.example.weatherforecast.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiServiceYa: ApiServiceYa,
    private val apiServiceWeather: ApiServiceOWeather
): WeatherRepository {
    override suspend fun getForecast(latitude: String, longitude: String) = apiServiceYa.getForecast(latitude, longitude)
    override suspend fun getGeo(cityName: String, apiKey: String) = apiServiceWeather.getLatLon(cityName, apiKey)
}