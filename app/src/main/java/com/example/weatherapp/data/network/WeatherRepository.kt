package com.example.weatherapp.data.network

import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.model.WeatherResponse
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherApiService: WeatherApiService) {


    var cod: Int = 0

    suspend fun getWeather(query: String): Weather? {
        try {
            if (cod != 200) {
                val response: WeatherResponse = weatherApiService.getWeather("690f96c414f84eb19ad04207242805", query = query)
                println("Codigo error: " + cod)
                println("Mensaje Response: " + response)
                println("temperature: " + response.current.temp_c)
                println("humidity: " + response.current.humidity)
                println("condition: " + response.current.condition.text)
  /*
                println("date 0: " + response.forecast.forecastday[0].date)
                println("maxTemp 0: " +response.forecast.forecastday[0].day.maxTemp)
                println("minTemp 0: " + response.forecast.forecastday[0].day.minTemp)
                println("date 1: " + response.forecast.forecastday[1].date)
                println("maxTemp 1: " +response.forecast.forecastday[1].day.maxTemp)
                println("minTemp 1: " +response.forecast.forecastday[1].day.minTemp)
                println("date 2: " + response.forecast.forecastday[2].date)
                println("maxTemp 2: " +response.forecast.forecastday[2].day.maxTemp)
                println("minTemp 2: " +response.forecast.forecastday[2].day.minTemp)
                println("date 3: " + response.forecast.forecastday[3].date)
                println("maxTemp 3: " +response.forecast.forecastday[3].day.maxTemp)
                println("minTemp 3: " +response.forecast.forecastday[3].day.minTemp)

  */
                println("LLegue a la respuesta de la API")
                println("veremos que responde en toWeatherResponse")
                return response.toWeatherResponse()
            } else {
                throw Exception("Error en la respuesta de la API")
            }
        } catch (e: Exception) {
            // Aquí puedes agregar más detalles del mensaje de excepción
            println("Error al obtener el clima: ${e.message}")
            return null
        }
    }

    suspend fun searchCities(cities: List<City>): List<WeatherResponse> {
        val weatherResponses = mutableListOf<WeatherResponse>()
        for (city in cities) {
            val response: WeatherResponse = weatherApiService.getWeather("690f96c414f84eb19ad04207242805", city.name)
            weatherResponses.add(response)
        }
        return weatherResponses
    }
}

fun WeatherResponse.toWeatherResponse(): Weather {
    return Weather(
        temp_c = this.current.temp_c,
        humidity = this.current.humidity,
        condition = this.current.condition.text
    )
}

/*
fun WeatherResponse.toWeatherResponse(): Weather {
    return Weather(
        temp_c = this.current.temp_c,
        humidity = this.current.humidity,
        condition = this.current.condition.text,
        forecast = this.forecast.forecastday.map { day ->
            Weather.Forecast(
                day = day.date,
                maxTemp = day.day.maxTemp,
                minTemp = day.day.minTemp
            )
        }
    )
}
*/