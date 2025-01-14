package com.example.weatherapp.data.model

data class WeatherResponse(
    val current: CurrentWeather,
   // val forecast: ForecastWeather
)

data class CurrentWeather(
    val temp_c: Float,
    val humidity: Int,
    val condition: Condition
)

data class Condition(
    val text: String
)
/*
data class ForecastWeather(
    val forecastday: List<ForecastDay>
)
*/
data class ForecastDay(
    val date: String,
    val day: Day
)

data class Day(
    val maxTemp: Float,
    val minTemp: Float
)
