package nikita.awraimow.weather.ui.forecast

sealed class ForecastScreenState {
    data object Loading: ForecastScreenState()
    data class Loaded(
        val forecast: ForecastUiModel
    ): ForecastScreenState()
}