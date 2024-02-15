package nikita.awraimow.weather.data.network

import nikita.awraimow.weather.data.locations.LocationResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoService {

    //https://api.openweathermap.org/geo/1.0/direct?q=London&limit=5
    // ?q={city name}&limit=5&appid=$TOKEN
    @GET("direct")
    suspend fun getLocations(
        @Query("q") cityName: String,
        @Query("appid") appid: String = TOKEN
    ): List<LocationResponseModel>

    private companion object { // fixme
        private const val TOKEN = "4aff0d93fc6fb6fd2fd195632dc9bbc1"
    }
}