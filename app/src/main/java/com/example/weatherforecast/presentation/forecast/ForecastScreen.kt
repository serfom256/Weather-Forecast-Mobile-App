package com.example.weatherforecast.presentation.forecast


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherforecast.common.Constants.PADDING_SCREEN_HORIZONTAL
import com.example.weatherforecast.common.Constants.SPACER_SIZE
import com.example.weatherforecast.domain.model.ForecastPeriodOptions
import com.example.weatherforecast.presentation.common_components.ButtonComponent
import com.example.weatherforecast.presentation.common_components.TabRowComponent
import com.example.weatherforecast.presentation.common_components.TextFieldComponent
import com.example.weatherforecast.presentation.common_components.ToolbarComponent
import com.example.weatherforecast.presentation.forecast.components.WeatherBoard
import com.google.android.gms.location.LocationServices

@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val forecastState = viewModel.forecastState.value
    val geoState = viewModel.geoState.value

    Scaffold(
        topBar = { ToolbarComponent() },
        content = {
            LazyColumn(
                modifier = Modifier.padding(horizontal = PADDING_SCREEN_HORIZONTAL),
                content = {
                    item {
                        Spacer(modifier = Modifier.size(SPACER_SIZE))
                        TextFieldComponent(
                            modifier = Modifier.fillMaxWidth(),
                            currentValue = viewModel.cityFieldState,
                            onValueChange = { viewModel.setCityField(it) },
                            trailingIcon = {
                                IconButton(
                                    enabled = !(forecastState.isLoading || geoState.isLoading),
                                    onClick = { viewModel.onSearchButtonClick() },
                                    content = {
                                        Icon(
                                            imageVector = Icons.Outlined.Search,
                                            contentDescription = "Поиск",
                                            tint = MaterialTheme.colors.primary
                                        )
                                    }
                                )
                            },
                            placeholder = "Населенный пункт"
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.size(SPACER_SIZE))
                        ButtonComponent(
                            text = "Погода в текущем городе",
                            onClick = { viewModel.onCityButtonClick() },
                            enabled = !(forecastState.isLoading || geoState.isLoading)
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.size(SPACER_SIZE))
                        TabRowComponent(
                            currentOption = viewModel.periodState.value,
                            listOptions = ForecastPeriodOptions.getPeriods(),
                            onClick = { newChosenOption ->
                                viewModel.onTabPeriodClick(newChosenOption)
                            }
                        )
                        Spacer(modifier = Modifier.size(SPACER_SIZE))
                    }
                    item {
                        if (forecastState.isLoading || geoState.isLoading) {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                content = {
                                    CircularProgressIndicator()
                                }
                            )
                        } else if (forecastState.error.isNotBlank() || geoState.error.isNotBlank()) {
                            Text(text = forecastState.error + geoState.error)
                        } else if (forecastState.forecasts.isNotEmpty()) {
                            WeatherBoard(
                                columnData = viewModel.weatherBoardState
                            )
                        } else { // в идеале прицепить состояние isEmpty, немного не успел
                            // а еще здесь писать текст не лучшая практика, извините <D
                            Text(text = "Чтобы узнать погоду, введите название города в текстовом поле " +
                                    "и нажмите на кнопку 'лупа', или включите геолокацию и нажмите на кнопку " +
                                    "для определения погоды в текущем городе"
                            )
                        }
                    }
                }
            )
        }
    )
}