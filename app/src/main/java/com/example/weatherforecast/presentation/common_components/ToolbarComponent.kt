package com.example.weatherforecast.presentation.common_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.common.Constants

@Composable
fun ToolbarComponent(title: String = Constants.APP_NAME_RUS) {
    TopAppBar (
        modifier = Modifier.height(Constants.TOOLBAR_HEIGHT),
        content = {
            Text(
                text = title,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    )
}