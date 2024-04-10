package com.example.weatherforecast.domain.model

interface TabRowSwitchable {
    val title: String
    val index: Int
    fun getByIndex(index: Int): TabRowSwitchable
}