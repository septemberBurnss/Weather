package nikita.awraimow.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nikita.awraimow.weather.ui.ForecastDetailsScreen
import nikita.awraimow.weather.ui.ForecastScreen
import nikita.awraimow.weather.ui.LocationsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Locations.route) {
        composable(route = Destination.Locations.route) {
            LocationsScreen(navController, hiltViewModel())
        }
        composable(route = Destination.Forecast.route) {
            ForecastScreen(navController)
        }
        composable(route = Destination.ForecastDetails.route) {
            ForecastDetailsScreen(navController)
        }
    }
}