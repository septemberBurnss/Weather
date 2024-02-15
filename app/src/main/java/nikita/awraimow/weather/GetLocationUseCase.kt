package nikita.awraimow.weather

import nikita.awraimow.weather.data.network.GeoService
import nikita.awraimow.weather.ui.locations.LocationMapper
import nikita.awraimow.weather.ui.locations.LocationModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetLocationUseCase @Inject constructor(
    private val geoService: GeoService,
    private val locationMapper: LocationMapper,
) {

    suspend fun getLocation(cityName: String): LocationModel {
        return locationMapper.mapLocation(
            geoService.getLocations(cityName).first()
        )
    }
}
