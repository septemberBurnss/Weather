package nikita.awraimow.weather.ui.detailedforecast

import nikita.awraimow.weather.ui.forecast.DayForecastUiModel
import nikita.awraimow.weather.ui.forecast.ForecastUiModel

sealed class ForecastDetailsScreenState {
    data object Loading: ForecastDetailsScreenState()
    data class Loaded(
        val forecast: DayForecastUiModel
    ): ForecastDetailsScreenState()
}