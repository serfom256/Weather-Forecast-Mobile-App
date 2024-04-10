package com.example.weatherforecast.presentation.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.common.Constants.CORNER_SIZE
import com.example.weatherforecast.common.Constants.CORNER_SIZE_ACTIVE
import com.example.weatherforecast.domain.model.TabRowSwitchable

@Composable
fun TabRowComponent(
    currentOption: TabRowSwitchable,
    listOptions: List<TabRowSwitchable>,
    onClick: (newChosenOption: TabRowSwitchable) -> Unit,
    shape: CornerBasedShape = RoundedCornerShape(CORNER_SIZE_ACTIVE)
) {
    val state = currentOption.index

    TabRow(
        selectedTabIndex = state,
        modifier = Modifier.height(40.dp),
        backgroundColor = Color.Transparent,
        indicator = {  },
        divider = {  },
        tabs = {
            listOptions.forEachIndexed { index, item ->
                Tab(
                    text = { Text(item.title) },
                    selected = state == index,
                    onClick = { onClick(currentOption.getByIndex(index)) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .background(
                            color = if (state == index) MaterialTheme.colors.primary else Color.Transparent,
                            shape = shape
                        )
                )
            }
        }
    )
}