package nikita.awraimow.weather.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nikita.awraimow.weather.GetLocationUseCase
import nikita.awraimow.weather.data.LocationsRepository
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val locationsRepository: LocationsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<LocationsScreenState>(LocationsScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadSavedLocations() {
        viewModelScope.launch {
            val locations = locationsRepository.getSavedLocations()
            if (locations.isEmpty()) {
                _uiState.emit(LocationsScreenState.NoLocations)
            } else {
                _uiState.emit(LocationsScreenState.Loaded(locations))
            }
        }
    }

    fun addCity(cityName: String) {
        viewModelScope.launch {
            val location = getLocationUseCase.getLocation(cityName)
            locationsRepository.addLocation(location)
            loadSavedLocations()
        }
    }
}
