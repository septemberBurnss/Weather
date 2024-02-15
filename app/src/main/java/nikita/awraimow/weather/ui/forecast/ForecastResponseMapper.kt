package nikita.awraimow.weather.ui.forecast

import nikita.awraimow.weather.data.forecast.ForecastResponseModel
import java.util.Calendar
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastResponseMapper @Inject constructor(
    private val calendar: Calendar
) {

    fun mapForecastResponseModel(model: ForecastResponseModel): ForecastUiModel {
        val title = model.city.name
        val days = model.list.map { dayResponse ->
            calendar.time = Date(dayResponse.dt * 1000) // epoch
            DayForecastUiModel(
                dayName = getWeekDay(calendar.get(Calendar.DAY_OF_WEEK)),
                weatherType = 0, // TODO
                minTemperature = dayResponse.temp.min.toInt(),
                maxTemperature = dayResponse.temp.max.toInt(),
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
}