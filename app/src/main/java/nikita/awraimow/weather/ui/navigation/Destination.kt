package nikita.awraimow.weather.ui.navigation

sealed class Destination(open val route: String) {
        data object Locations: Destination("Locations")
        data object Forecast: Destination("Location")
}

fun Destination.withParam(param: String): String {
    return route.substringBefore("/").plus("/").plus(param)
}