package com.example.weatherforecast.presentation.forecast.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.common.Constants.CORNER_SIZE

@Composable
fun WeatherBoard(
    columnData: List<List<String>>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(CORNER_SIZE)
            ),
        horizontalArrangement = Arrangement.SpaceAround,
        content = {
            columnData.forEach { curColumn ->
                Column(
                    content = {
                        curColumn.forEach { curElem ->
                            Text(
                                text = curElem,
                                modifier = Modifier.padding(vertical = 10.dp)
                            )
                        }
                    }
                )
            }
        }
    )
}