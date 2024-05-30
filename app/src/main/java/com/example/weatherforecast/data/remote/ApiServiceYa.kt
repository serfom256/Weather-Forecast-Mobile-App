package com.example.weatherforecast.data.remote

import com.example.weatherforecast.data.remote.dto.WeatherTotalDto
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceYa {

    @Headers("X-Yandex-API-Key: 1ed3100a-3327-4e98-a64b-938da332a7b0")
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
    ): Response<WeatherTotalDto>
}