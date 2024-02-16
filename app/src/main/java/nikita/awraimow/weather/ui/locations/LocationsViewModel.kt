package nikita.awraimow.weather.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nikita.awraimow.weather.AddLocationUseCase
import nikita.awraimow.weather.GetLocationUseCase
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val addLocationUseCase: AddLocationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LocationsScreenState>(LocationsScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadSavedLocations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val locations = getLocationUseCase.getSavedLocations()
                if (locations.isEmpty()) {
                    _uiState.emit(LocationsScreenState.NoLocations)
                } else {
                    _uiState.emit(LocationsScreenState.Loaded(locations))
                }
            }
        }
    }

    fun addCity(cityName: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                addLocationUseCase.addLocation(cityName)
                loadSavedLocations()
            }
        }
    }
}
