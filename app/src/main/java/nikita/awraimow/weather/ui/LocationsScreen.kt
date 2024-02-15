package nikita.awraimow.weather.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import nikita.awraimow.weather.ui.navigation.Destination
import nikita.awraimow.weather.ui.theme.WeatherTheme

@Composable
fun LocationsScreen(
    navController: NavController, viewModel: LocationsViewModel
) {
    var city by remember { mutableStateOf("") }
    WeatherTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(value = city, onValueChange = { city = it })
            Button(onClick = {
                viewModel.addCity(city)
                navController.navigate(Destination.Forecast.route)
            }) {
                Text(text = "Add")
            }
        }
    }
}