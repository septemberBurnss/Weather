package nikita.awraimow.weather.data.network

import nikita.awraimow.weather.data.forecast.ForecastResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("daily")
    suspend fun getWeatherForLocation(
        @Query("lat") lattitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") appid: String = TOKEN,
        @Query("units") units: String = "metric"
    ): ForecastResponseModel

    private companion object { // fixme store locally
        private const val TOKEN = "4aff0d93fc6fb6fd2fd195632dc9bbc1"
    }
}