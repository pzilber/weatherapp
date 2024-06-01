package com.example.weatherapp.ui.cities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp.viewmodel.CityViewModel

@Composable
fun CityScreen(navController: NavController, cityViewModel: CityViewModel = hiltViewModel()) {
    var cityName by remember { mutableStateOf(TextFieldValue("")) }
    val cities by cityViewModel.cities.collectAsState()

    LaunchedEffect(Unit) {
        cityViewModel.loadCities()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            TextField(
                value = cityName,
                onValueChange = { cityName = it },
                label = { Text("Enter city name") },
                modifier = Modifier
                    .weight(1f)
                    .onKeyEvent { keyEvent ->
                        if (keyEvent.type == KeyEventType.KeyUp && keyEvent.key == Key.Enter) {
                            cityViewModel.searchCity(cityName.text)
                            true
                        } else {
                            false
                        }
                    }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { cityViewModel.searchCity(cityName.text) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Search")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { cityViewModel.findCurrentLocation() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Use Current Location")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(cities) { city ->
                Text(
                    text = city.name,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            cityViewModel.selectCity(city)
                            navController.navigate("weather")
                            println("Navigating to weather screen for city: ${city.name}")
                        }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
