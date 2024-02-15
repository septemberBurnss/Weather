package nikita.awraimow.weather.ui.locations

sealed class LocationsScreenState {
    data object Loading: LocationsScreenState()
    data object NoLocations: LocationsScreenState()
    data class Loaded(
        val locations: List<LocationModel>
    ): LocationsScreenState()
}