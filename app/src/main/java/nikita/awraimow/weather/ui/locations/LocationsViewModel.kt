package nikita.awraimow.weather.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val addLocationUseCase: AddLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LocationsScreenState>(LocationsScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun addCity(cityName: String) {
        viewModelScope.launch {
            val temp = addLocationUseCase.addLocation(cityName)
            _uiState.value = LocationsScreenState.Loaded(listOf(
                temp
            ))
        }
    }
}
