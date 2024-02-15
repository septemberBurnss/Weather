package nikita.awraimow.weather.ui.forecast

import nikita.awraimow.weather.data.forecast.ForecastResponseModel
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.roundToInt

@Singleton
class ForecastResponseMapper @Inject constructor(
    private val calendar: Calendar
) {

    fun mapForecastResponseModel(model: ForecastResponseModel): ForecastUiModel {
        val title = model.city.name
        val days = model.list.map { dayResponse ->
            calendar.time = Date(dayResponse.dt * 1000) // epoch
            val dayName = getWeekDay(calendar.get(Calendar.DAY_OF_WEEK))
            val monthSuffix = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
            val daySuffix = calendar.get(Calendar.DAY_OF_MONTH)

            calendar.time = Date(dayResponse.sunrise * 1000)
            val sunrise = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
            calendar.time = Date(dayResponse.sunset * 1000)
            val sunset = "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"

            DayForecastUiModel(
                title = model.city.name,
                date = dayResponse.dt,
                dayName = dayName,
                dateSuffix = "$monthSuffix $daySuffix",
                weatherIconUrl = "$ICON_BASE_URL${dayResponse.weather.first().icon}@4x.png",
                temperature = dayResponse.temp.day.roundToInt(),
                minTemperature = dayResponse.temp.min.roundToInt(),
                maxTemperature = dayResponse.temp.max.roundToInt(),
                feelsLike = dayResponse.feels_like.day.roundToInt(),
                humidity = dayResponse.humidity,
                pressure = dayResponse.pressure,
                windSpeed = dayResponse.speed.toString(),
                sunrise = sunrise,
                sunset = sunset
            )
        }
        return ForecastUiModel(title, days)
    }

    private fun getWeekDay(day: Int): String {
        return when(day) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> throw Exception("Invalid input")
        }
    }

    private companion object {
        private const val ICON_BASE_URL = "https://openweathermap.org/img/wn/"
    }
}