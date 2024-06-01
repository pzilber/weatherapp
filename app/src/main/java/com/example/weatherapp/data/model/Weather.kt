package com.example.weatherapp.data.model

data class Weather(
    val temp_c: Float,
    val humidity: Int,
    val condition: String,
    //val forecast: List<Forecast>
)
/*
{
    data class Forecast(
        val day: String,
        val maxTemp: Float,
        val minTemp: Float
    )
}
*/