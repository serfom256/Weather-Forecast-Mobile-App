package com.example.weatherforecast.di

import com.example.weatherforecast.common.Constants.BASE_URL_OW
import com.example.weatherforecast.common.Constants.BASE_URL_YA
import com.example.weatherforecast.data.remote.ApiServiceOWeather
import com.example.weatherforecast.data.remote.ApiServiceYa
import com.example.weatherforecast.data.repository.WeatherRepositoryImpl
import com.example.weatherforecast.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYaApi(): ApiServiceYa {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_YA)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiServiceYa::class.java)
    }

    @Provides
    @Singleton
    fun provideOWeatherApi(): ApiServiceOWeather {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BASE_URL_OW)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(ApiServiceOWeather::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(
        apiServiceYa: ApiServiceYa,
        apiServiceWeather: ApiServiceOWeather
    ): WeatherRepository {
        return WeatherRepositoryImpl(apiServiceYa, apiServiceWeather)
    }
}