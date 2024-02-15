package nikita.awraimow.weather.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nikita.awraimow.weather.AddLocationUseCase
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val addLocationUseCase: AddLocationUseCase
) : ViewModel() {

    fun addCity(cityName: String) {
        Log.d("TAG", "adding $cityName")
    }
}
