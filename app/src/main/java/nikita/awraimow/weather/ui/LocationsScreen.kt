package nikita.awraimow.weather.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import nikita.awraimow.weather.ui.navigation.Destination

@Composable
fun LocationsScreen(
    navController: NavController, viewModel: LocationsViewModel
) {
    Text(text = "Locations")
    Button(onClick = {
        navController.navigate(Destination.Forecast.route)
    }) {
    }
}