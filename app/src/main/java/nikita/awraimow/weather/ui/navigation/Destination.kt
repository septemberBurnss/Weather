package nikita.awraimow.weather.ui.navigation

sealed class Destination(open val route: String) {
        data object Locations: Destination("Locations")
        data object Forecast: Destination("Forecast/{latitude}/{longitude}")
        data object ForecastDetails: Destination("ForecastDetails/{date}")
}

fun Destination.withParam(param: Long): String {
    return route.substringBefore("/").plus("/").plus(param)
}