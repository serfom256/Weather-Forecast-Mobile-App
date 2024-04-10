package com.example.weatherforecast.data.remote

import com.example.weatherforecast.data.remote.dto.GeoDto
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceOWeather {

    @GET("geo/1.0/direct")
    suspend fun getLatLon(
        @Query("q") cityName: String,
        @Query("appid") API_KEY: String
    ): Response<List<GeoDto>>
}