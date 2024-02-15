package nikita.awraimow.weather.ui.detailedforecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nikita.awraimow.weather.ui.forecast.GetForecastUseCase
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class ForecastDetailsViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ForecastDetailsScreenState>(ForecastDetailsScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getForecast(date: Long) {
        viewModelScope.launch {
            val forecast = getForecastUseCase.getForecastForDate(date)
            _uiState.value = ForecastDetailsScreenState.Loaded(forecast)
        }
    }
}