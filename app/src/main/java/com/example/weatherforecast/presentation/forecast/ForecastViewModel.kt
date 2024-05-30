package com.example.weatherforecast.presentation.forecast

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.common.Resource
import com.example.weatherforecast.common.Constants.DEFAULT_COORDINATE
import com.example.weatherforecast.domain.extensions.conditionToEmoji
import com.example.weatherforecast.domain.extensions.enDayToRus
import com.example.weatherforecast.domain.model.ForecastPeriodOptions
import com.example.weatherforecast.domain.model.ForecastState
import com.example.weatherforecast.domain.model.Geo
import com.example.weatherforecast.domain.model.GeoState
import com.example.weatherforecast.domain.model.TabRowSwitchable
import com.example.weatherforecast.domain.model.getDayOfWeek
import com.example.weatherforecast.domain.use_case.get_forecast.GetForecastUseCase
import com.example.weatherforecast.domain.use_case.get_geo.GetGeoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase,
    private val getGeoUseCase: GetGeoUseCase
) : ViewModel() {

    // TextField
    var cityFieldState by mutableStateOf("")
        private set

    fun setCityField(newState: String) {
        cityFieldState = newState
    }

    fun onSearchButtonClick() {
        if (cityFieldState.isNotBlank()) {
            getForecastByCityName(cityFieldState)
        }
    }

    // cityButton
    fun onCityButtonClick() {
        setCityField("")
        with(_userGeoState.value) {
            if (this.latitude != DEFAULT_COORDINATE) getForecast(this)
        }
    }

    // TabRow
    private val _periodState: MutableState<TabRowSwitchable> =
        mutableStateOf(ForecastPeriodOptions.TODAY)
    val periodState: State<TabRowSwitchable> = _periodState
    fun onTabPeriodClick(newChosenOption: TabRowSwitchable) {
        _periodState.value = newChosenOption
        updateWeatherBoard()
    }

    // WeatherBoard
    var weatherBoardState by mutableStateOf(listOf(listOf("")))
        private set

    private fun updateWeatherBoard() {
        when (_periodState.value) {
            ForecastPeriodOptions.TODAY -> updateWbForToday()
            ForecastPeriodOptions.WEEK -> updateWbForWeek()
        }
    }

    private fun updateWbForToday() {
        if (_forecastState.value.forecasts.isNotEmpty()) {
            val temp = _forecastState.value.forecasts[0].perHours
            val condition = mutableListOf<String>()
            val hour = mutableListOf<String>()
            val temperature = mutableListOf<String>()
            val windSpeed = mutableListOf<String>()
            for (i in 0..21 step 3) {
                hour.add(temp[i].hour + ":00")
                condition.add(temp[i].condition.conditionToEmoji())
                windSpeed.add(temp[i].windSpeed.toString() + " м/с")
                temperature.add(temp[i].temp.toString() + "°C")
            }
            weatherBoardState = listOf(hour, condition, windSpeed, temperature)
        }
    }

    private fun updateWbForWeek() {
        val temp = _forecastState.value.forecasts
        val condition = mutableListOf<String>()
        val day = mutableListOf<String>()
        val temperature = mutableListOf<String>()
        val windSpeed = mutableListOf<String>()
        temp.forEach {
            day.add(getDayOfWeek(it.dateTs).enDayToRus())
            condition.add(it.condition.conditionToEmoji())
            windSpeed.add(it.windSpeed.toString() + " м/с")
            temperature.add(it.temp.toString() + "°C")
        }
        weatherBoardState = listOf(day, condition, windSpeed, temperature)
    }

    private val _forecastState = mutableStateOf(ForecastState())
    val forecastState: State<ForecastState> = _forecastState

    fun getForecast(geo: Geo) {
        getForecastUseCase.getForecast(
            latitude = geo.latitude.toString(),
            longitude = geo.longitude.toString()
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _forecastState.value = ForecastState(forecasts = result.data ?: emptyList())
                    updateWeatherBoard()
                }

                is Resource.Error -> {
                    _forecastState.value = ForecastState(error = "Error ${result.message}")
                }

                is Resource.Loading -> {
                    _forecastState.value = ForecastState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _geoState = mutableStateOf(GeoState())
    val geoState: State<GeoState> = _geoState

    private fun getForecastByCityName(cityName: String) {
        getGeoUseCase.getGeo(
            cityName = cityName,
            apiKey = "0d1c49d9637dc9599e4455ae626ee3f7"
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _geoState.value = GeoState(geo = result.data)
                    getForecast(_geoState.value.geo!!)
                }

                is Resource.Error -> {
                    _geoState.value = GeoState(error = "Error ${result.message}")
                }

                is Resource.Loading -> {
                    _geoState.value = GeoState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private val _userGeoState = mutableStateOf(Geo())
    fun setUserGeo(newState: Geo) {
        _userGeoState.value = newState
    }
}