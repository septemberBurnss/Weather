package nikita.awraimow.weather.ui.navigation

sealed class Destination(open val route: String) {
        data object Locations: Destination("Locations")
        data object Forecast: Destination("Forecast/{latitude}/{longitude}")
        data object ForecastDetails: Destination("ForecastDetails/{date}")
}

//fixme
fun Destination.ForecastDetails.withParam(param: Long): String {
    return route.substringBefore("/").plus("/").plus(param)
}

//fixme
fun Destination.Forecast.withParams(param1: Double, param2: Double): String {
    return route.substringBefore("/").plus("/").plus(param1).plus("/").plus(param2)
}