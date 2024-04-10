package com.example.weatherforecast.data.remote

import com.example.weatherforecast.data.remote.dto.WeatherTotalDto
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceYa {

    @Headers("X-Yandex-API-Key: be652545-e359-404a-8a62-704222ebe7b7")
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
    ): Response<WeatherTotalDto>
}