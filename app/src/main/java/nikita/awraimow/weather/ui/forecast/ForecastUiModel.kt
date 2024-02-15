package nikita.awraimow.weather.ui.forecast

data class ForecastUiModel(
    val title: String,
    val days: List<DayForecastUiModel>
)

data class DayForecastUiModel(
    val date: Long,
    val dayName: String,
    val weatherType: Int,
    val temperature: Int,
    val minTemperature: Int,
    val maxTemperature: Int,
    val feelsLike: Int,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: String,
    val sunrise: String,
    val sunset: String
)