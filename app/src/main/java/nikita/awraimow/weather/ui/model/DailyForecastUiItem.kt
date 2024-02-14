package nikita.awraimow.weather.ui.model

data class DailyForecastUiItem(
    val day: String,
    val weather: Int,
    val maxTemperature: String,
    val minTemperature: String
)