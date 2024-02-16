package nikita.awraimow.weather.data

import nikita.awraimow.weather.data.locations.LocationPrefsWrapper
import nikita.awraimow.weather.ui.locations.LocationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationsRepository @Inject constructor(
    private val locationPreferences: LocationPrefsWrapper
) {

    fun addLocation(location: LocationModel) {
        locationPreferences.saveLocation(location)
    }

    fun getSavedLocations(): List<LocationModel> {
        return locationPreferences.getAllSavedLocations()
    }
}