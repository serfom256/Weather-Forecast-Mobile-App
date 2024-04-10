package com.example.weatherforecast.domain.use_case.get_geo

import android.util.Log
import com.example.weatherforecast.common.Resource
import com.example.weatherforecast.data.remote.dto.toGeo
import com.example.weatherforecast.domain.model.Geo
import com.example.weatherforecast.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGeoUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    fun getGeo(cityName: String, apiKey: String): Flow<Resource<Geo>> = flow {
        try {
            emit(Resource.Loading())
            Log.d("GetGeoUseCase1", "111")
            val geo = repository.getGeo(cityName = cityName, apiKey = apiKey)
            Log.d("GetGeoUseCase2", "2 $geo")
            emit(Resource.Success(geo.body()!![0].toGeo()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection"))
        } catch (e: IndexOutOfBoundsException) {
            emit(Resource.Error("This city is not found"))
        }
    }
}