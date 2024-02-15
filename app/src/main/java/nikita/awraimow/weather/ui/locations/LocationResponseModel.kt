package nikita.awraimow.weather.ui.locations

data class LocationResponseModel(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String?,
    val state: String?
)