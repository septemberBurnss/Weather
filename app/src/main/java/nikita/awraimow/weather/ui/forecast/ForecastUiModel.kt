package nikita.awraimow.weather.ui.forecast

data class ForecastUiModel(
    val title: String,
    val days: List<DayForecastUiModel>
)

data class DayForecastUiModel(
    val dayName: String,
    val weatherType: Int,
    val minTemperature: Int,
    val maxTemperature: Int
)