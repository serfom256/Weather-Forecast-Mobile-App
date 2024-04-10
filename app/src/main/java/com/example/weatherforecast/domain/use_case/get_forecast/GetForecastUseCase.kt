package com.example.weatherforecast.domain.use_case.get_forecast

import android.util.Log
import com.example.weatherforecast.common.Resource
import com.example.weatherforecast.data.remote.dto.toForecast
import com.example.weatherforecast.domain.model.Forecast
import com.example.weatherforecast.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    fun getForecast(latitude: String, longitude: String): Flow<Resource<List<Forecast>>> = flow {
        try {
            emit(Resource.Loading())
            Log.d("UseCase", "111")
            val forecasts = repository.getForecast(latitude = latitude, longitude = longitude)
            Log.d("UseCase", "2 $forecasts")
            emit(Resource.Success(forecasts.body()!!.forecasts.map { it.toForecast() }))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection"))
        }
    }
}