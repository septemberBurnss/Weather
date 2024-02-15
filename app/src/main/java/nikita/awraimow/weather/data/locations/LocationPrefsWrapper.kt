package nikita.awraimow.weather.data.locations

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import nikita.awraimow.weather.ui.locations.LocationModel
import javax.inject.Inject

// todo replace with datastore
class LocationPrefsWrapper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val gson: Gson
) {

    private val sharedPreferences = context.getSharedPreferences(
        LOCATION_TABLE_NAME,
        Context.MODE_PRIVATE
    )

    fun saveLocation(location: LocationModel) {
        val editor = sharedPreferences.edit()
        val savedData = getAllSavedLocations()
        if (!savedData.contains(location)) {
            editor.putString(
                SAVED_LOCATIONS_PREF_KEY, gson.toJson(savedData.plus(location))
            )
        }
        editor.apply()
    }

    fun getAllSavedLocations(): List<LocationModel> {
        val savedJson = sharedPreferences.getString(SAVED_LOCATIONS_PREF_KEY, null)
        if (savedJson.isNullOrEmpty()) return emptyList()
        return gson.fromJson(savedJson, Array<LocationModel>::class.java).toList()
    }

    private companion object {
        private const val LOCATION_TABLE_NAME = "saved_locations"
        private const val SAVED_LOCATIONS_PREF_KEY = "saved_locations"
    }
}