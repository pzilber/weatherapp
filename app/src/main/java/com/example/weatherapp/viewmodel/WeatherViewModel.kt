package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.network.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> get() = _weather

    fun loadWeather(city: City) {
        println("Llegue al WeatherViewModel 1")
        viewModelScope.launch {
            try {
                val weatherData = repository.getWeather(city.name)
                println("Weather data: $weatherData")
                _weather.value = weatherData
                println("Weather data loaded: $weatherData")
                println("Weather value: ${_weather.value}")
                println(weather)
                println("Llegue al WeatherViewModel")
            } catch (e: Exception) {
                _weather.value = null

                println(e.message)
                println("Error en el WeatherViewModel")
                println("Error loading weather: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}

/*
package com.example.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.model.City
import com.example.weatherapp.data.model.Weather
import com.example.weatherapp.data.network.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> get() = _weather

    fun loadWeather(city: City) {
        viewModelScope.launch {
            try {
                val weatherData = weatherRepository.fetchWeatherData(city)
                _weather.value = weatherData
            } catch (e: Exception) {
                _weather.value = null
            }
        }
    }
}
*/