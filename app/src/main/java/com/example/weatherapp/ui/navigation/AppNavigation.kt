package com.example.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.ui.cities.CityScreen
import com.example.weatherapp.ui.weather.WeatherScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "cities") {
        composable("cities") { CityScreen(navController = navController) }
        composable("weather") { WeatherScreen(navController = navController) }
    }
}
