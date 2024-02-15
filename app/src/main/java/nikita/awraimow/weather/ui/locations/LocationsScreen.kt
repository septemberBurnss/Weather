package nikita.awraimow.weather.ui.locations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nikita.awraimow.weather.ui.theme.WeatherTheme

@Composable
fun LocationsScreen(
    navController: NavController, viewModel: LocationsViewModel
) {
    val state = viewModel.uiState.collectAsState().value
    var city by remember { mutableStateOf("") }

    WeatherTheme {
        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                textAlign = TextAlign.Center,
                text = "Your locations",
                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
            )

            if (state is LocationsScreenState.Loaded) {
                LocationItem(state.locations[0]) {
                    navController.navigate(
                        "Forecast/${it.latitude}/${it.longitude}"
                    )
                }
            }

            TextField(
                value = city,
                onValueChange = { city = it }
            )
            Button(onClick = {
                viewModel.addCity(city)
                // navController.navigate(Destination.Forecast.route)
            }) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
fun LocationItem(location: LocationModel, onLocationClick: (LocationModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onLocationClick.invoke(location) }
            .padding(16.dp)
    ) {
        Text(text = location.name)
        Text(text = "(${location.country})")
    }
}