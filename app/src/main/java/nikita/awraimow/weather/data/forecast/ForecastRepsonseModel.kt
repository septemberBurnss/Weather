package nikita.awraimow.weather.data.forecast

data class ForecastResponseModel(
    val city: CityResponseModel,
    val list: List<DailyForecastResponseModel>,
    val cnt: Int
)

data class CityResponseModel(
    val name: String
)

data class DailyForecastResponseModel(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: TemperatureResponseModel,
    val weather: List<WeatherTypeResponseModel>
)

data class TemperatureResponseModel(
    val day: Double,
    val night: Double,
    val min: Double,
    val max: Double,
    val eve: Double,
    val morn: Double
)

data class WeatherTypeResponseModel(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)