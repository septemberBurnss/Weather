package nikita.awraimow.weather.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nikita.awraimow.weather.ui.detailedforecast.DetailedForecastScreen
import nikita.awraimow.weather.ui.forecast.ForecastScreen
import nikita.awraimow.weather.ui.locations.LocationsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.Locations.route
    ) {
        composable(route = Destination.Locations.route) {
            LocationsScreen(navController, hiltViewModel())
        }
        composable(
            route = Destination.Forecast.route,
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.FloatType
                },
                navArgument("longitude") {
                    type = NavType.FloatType
                }
            )
        ) {
            val latitude = it.arguments?.getFloat("latitude") ?: -1f
            val longitude = it.arguments?.getFloat("longitude") ?: -1f
            ForecastScreen(
                navController,
                hiltViewModel(),
                latitude.toDouble(),
                longitude.toDouble()
            )
        }
        composable(
            route = Destination.ForecastDetails.route,
            arguments = listOf(
                navArgument("date") {
                    type = NavType.LongType
                }
            )
        ) {
            val date = it.arguments?.getLong("date") ?: -1
            DetailedForecastScreen(date, hiltViewModel())
        }
    }
}