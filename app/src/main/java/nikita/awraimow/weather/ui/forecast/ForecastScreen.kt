package nikita.awraimow.weather.ui.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import nikita.awraimow.weather.R
import nikita.awraimow.weather.ui.navigation.Destination
import nikita.awraimow.weather.ui.navigation.withParam
import nikita.awraimow.weather.ui.theme.WeatherTheme

@Composable
fun ForecastScreen(
    navController: NavController,
    viewModel: ForecastViewModel,
    latitude: Double,
    longitude: Double
) {
    val state = viewModel.uiState.collectAsState().value
    LaunchedEffect(key1 = latitude, key2 = longitude) {
        viewModel.getForecast(latitude, longitude)
    }
    WeatherTheme {
        when {
            state is ForecastScreenState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            state is ForecastScreenState.Loaded -> {
                Loaded(state = state) {
                    navController.navigate(Destination.ForecastDetails.withParam(it))
                }
            }
        }
    }
}

@Composable
fun Loaded(
    state: ForecastScreenState.Loaded,
    onDaySelected: (Long) -> Unit
) {
    Column {
        Text(
            textAlign = TextAlign.Center,
            text = state.forecast.title,
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        for (item in state.forecast.days) {
            DailyForecastItem(item = item) {
                onDaySelected(item.date)
            }
        }
    }
}

@Composable
fun DailyForecastItem(item: DayForecastUiModel, onDaySelected: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onDaySelected()
            }
            .padding(24.dp)
    ) {
        Text(
            text = item.dayName,
            modifier = Modifier.weight(0.3f)
        )
        AsyncImage(
            model = item.weatherIconUrl,
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .weight(0.4f)
        )
        Text(
            text = "${item.maxTemperature}",
            color = Color.Black,
            modifier = Modifier.weight(0.15f)
        )
        Text(
            text = "${item.minTemperature}",
            color = Color.Gray,
            modifier = Modifier.weight(0.15f)
        )
    }
}
