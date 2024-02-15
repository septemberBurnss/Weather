package nikita.awraimow.weather.ui.locations

import javax.inject.Inject

class LocationMapper @Inject constructor() {

    fun mapLocation(response: LocationResponseModel): LocationModel {
        return LocationModel(
            name = response.name,
            country = response.country,
            latitude = response.lat,
            longitude = response.lon
        )
    }
}