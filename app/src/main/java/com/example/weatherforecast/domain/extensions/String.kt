package com.example.weatherforecast.domain.extensions

fun String.conditionToEmoji(): String {
    return when (this) {
        "clear" -> "☀️"
        "partly-cloudy" -> "\uD83C\uDF24️"
        "cloudy" -> "\uD83C\uDF25️"
        "overcast" -> "\uD83C\uDF26️"
        "drizzle", "light-rain", "rain", "moderate-rain", "heavy-rain",
            "continuous-heavy-rain", "showers" -> "\uD83C\uDF27️"
        "light-snow", "snow", "snow-showers", "hail" -> "\uD83C\uDF28️"
        else -> "⛈️"
    }
}

fun String.enDayToRus(): String {
    return when (this) {
        "Mon" -> "Пн"
        "Tue" -> "Вт"
        "Wed" -> "Ср"
        "Thu" -> "Чт"
        "Fri" -> "Пт"
        "Sat" -> "Сб"
        else -> "Вс"
    }
}