package nikita.awraimow.weather.ui.forecast

import nikita.awraimow.weather.data.forecast.ForecastResponseModel
import nikita.awraimow.weather.data.network.WeatherService
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastUseCase @Inject constructor(
    private val weatherService: WeatherService,
    private val mapper: ForecastResponseMapper
) {

    private var forecastForPreviouslySelectedLocation: ForecastUiModel? = null

    suspend fun getForecast(): ForecastUiModel {
        val result = mapper.mapForecastResponseModel(
            weatherService.getWeatherForLocation(52.2298, 21.0118)
        )
        forecastForPreviouslySelectedLocation = result
        return result
    }

    suspend fun getForecastForDate(date: Long): DayForecastUiModel {
        return forecastForPreviouslySelectedLocation?.days?.find {
            it.date == date
        } ?: throw Exception()
    }
}