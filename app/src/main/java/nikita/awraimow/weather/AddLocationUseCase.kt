package nikita.awraimow.weather

import nikita.awraimow.weather.data.LocationsRepository
import nikita.awraimow.weather.data.network.GeoService
import nikita.awraimow.weather.ui.locations.LocationMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddLocationUseCase @Inject constructor(
    private val geoService: GeoService,
    private val locationsRepository: LocationsRepository,
    private val locationMapper: LocationMapper,
) {

    suspend fun addLocation(cityName: String) {
        val location = locationMapper.mapLocation(
            geoService.getLocations(cityName).first()
        )
        locationsRepository.addLocation(location)
    }
}
