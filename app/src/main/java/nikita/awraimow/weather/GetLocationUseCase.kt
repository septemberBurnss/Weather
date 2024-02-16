package nikita.awraimow.weather

import nikita.awraimow.weather.data.LocationsRepository
import nikita.awraimow.weather.ui.locations.LocationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationUseCase @Inject constructor(
    private val locationsRepository: LocationsRepository
) {

    fun getSavedLocations(): List<LocationModel> {
        return locationsRepository.getSavedLocations()
    }
}
