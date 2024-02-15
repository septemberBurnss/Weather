package nikita.awraimow.weather.ui.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ForecastScreenState>(ForecastScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun getForecast() {
        viewModelScope.launch {
            val forecast = getForecastUseCase.getForecast()
            _uiState.value = ForecastScreenState.Loaded(forecast)
        }
    }
}