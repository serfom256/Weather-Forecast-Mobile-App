package com.example.weatherforecast.domain.model


enum class ForecastPeriodOptions(
    override val title: String,
    override val index: Int
) : TabRowSwitchable {
    TODAY("Сегодня", 0),
    WEEK("Неделя", 1);

    companion object {
        fun getPeriods(): List<TabRowSwitchable> {
            return listOf(TODAY, WEEK)
        }
    }

    override fun getByIndex(index: Int): TabRowSwitchable {
        return getPeriods().find { it.index == index }!!
    }
}