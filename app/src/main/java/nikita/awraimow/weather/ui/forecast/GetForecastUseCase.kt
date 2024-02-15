package nikita.awraimow.weather.ui.forecast

import nikita.awraimow.weather.data.network.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastUseCase @Inject constructor(
    private val weatherService: WeatherService,
    private val mapper: ForecastResponseMapper
) {

    private var forecastForPreviouslySelectedLocation: ForecastUiModel? = null

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ): ForecastUiModel {
        val result = mapper.mapForecastResponseModel(
            weatherService.getWeatherForLocation(latitude, longitude)
        )
        forecastForPreviouslySelectedLocation = result
        return result
    }

    fun getForecastForDate(date: Long): DayForecastUiModel {
        return forecastForPreviouslySelectedLocation?.days?.find {
            it.date == date
        } ?: throw Exception()
    }
}