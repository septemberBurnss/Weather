package nikita.awraimow.weather.ui.locations

import nikita.awraimow.weather.data.network.GeoService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddLocationUseCase @Inject constructor(
    private val geoService: GeoService,
    private val locationMapper: LocationMapper
) {

    suspend fun addLocation(cityName: String): LocationModel {
        return locationMapper.mapLocation(
            geoService.getLocations(cityName).first()
        )
    }
}
