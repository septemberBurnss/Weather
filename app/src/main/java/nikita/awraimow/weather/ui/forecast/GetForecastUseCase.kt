package nikita.awraimow.weather.ui.forecast

import nikita.awraimow.weather.data.network.WeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetForecastUseCase @Inject constructor(
    private val weatherService: WeatherService,
    private val mapper: ForecastResponseMapper
) {

    suspend fun getForecast(): ForecastUiModel {
        return mapper.mapForecastResponseModel(
            weatherService.getWeatherForLocation(52.2298, 21.0118)
        )
    }
}