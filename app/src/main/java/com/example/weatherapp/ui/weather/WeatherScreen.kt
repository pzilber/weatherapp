package com.example.weatherapp.ui.weather

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.viewmodel.CityViewModel
import com.example.weatherapp.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(
    navController: NavController,
    cityViewModel: CityViewModel = hiltViewModel(),
    weatherViewModel: WeatherViewModel = hiltViewModel()
) {
    val selectedCity by cityViewModel.selectedCity.collectAsState()
    val weather by weatherViewModel.weather.collectAsState()

    LaunchedEffect(selectedCity) {
        selectedCity?.let {
            println("Selected city: ${it.name}")
            weatherViewModel.loadWeather(it)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        weather?.let { weatherData ->
            // Recuadro utilizado para mostrar el clima
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFADD8E6)) // Color celeste
                    .padding(16.dp)
            ) {
                Column {
                    Text("City: ${selectedCity?.name}")
                    Text("Temperature: ${weatherData.temp_c} °C")
                    Text("Humidity: ${weatherData.humidity}%")
                    Text("Condition: ${weatherData.condition}")

                    Spacer(modifier = Modifier.height(16.dp))
                    /*
                    Text("7-Day Forecast:")
                    LazyRow {
                        items(weatherData.forecast) { forecast ->
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text("Day: ${forecast.day}")
                                Text("Max: ${forecast.maxTemp} °C")
                                Text("Min: ${forecast.minTemp} °C")
                            }
                        }
                    }
                    */
                }
            }
        }

/*            Spacer(modifier = Modifier.height(16.dp))

            // Recuadro para el gráfico de barras
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFADD8E6)) // Color celeste
                    .padding(16.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val barWidth = (size.width / (weather?.forecast?.size * 2)).toFloat()
                    val maxTemp = weatherData.forecast.maxOf { it.maxTemp }.toFloat()

                    weatherData.forecast.forEachIndexed { index, forecast ->
                        val barHeight = (forecast.maxTemp / maxTemp * size.height).toFloat()
                        drawRect(
                            color = Color.Blue,
                            topLeft = androidx.compose.ui.geometry.Offset(
                                x = index * 2 * barWidth,
                                y = size.height - barHeight
                            ),
                            size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
                        )
                    }
                }
            }
        }
        */

        ?: run {
            // Muestra un mensaje de carga o error si los datos no están disponibles
            Text("Loading weather data or unable to fetch data.")
            println("Weather data is null")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("cities") }) {
            Text("Change City")
        }
    }
}
