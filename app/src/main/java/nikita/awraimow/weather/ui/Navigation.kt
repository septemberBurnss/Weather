package nikita.awraimow.weather.ui

sealed class Screen() {
    data object Locations: Screen()
    data object Forecast: Screen()
    data object ForecastDetails: Screen()
}