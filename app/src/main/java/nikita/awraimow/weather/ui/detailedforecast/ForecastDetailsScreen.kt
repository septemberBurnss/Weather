package nikita.awraimow.weather.ui.detailedforecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import nikita.awraimow.weather.R
import nikita.awraimow.weather.ui.forecast.DayForecastUiModel

@Composable
fun DetailedForecastScreen(
    date: Long,
    viewModel: ForecastDetailsViewModel
) {

    val state = viewModel.uiState.collectAsState().value

    if (state is ForecastDetailsScreenState.Loaded) {
        Loaded(state.forecast)
    }

    viewModel.getForecast(date)
}

@Composable
fun Loaded(forecast: DayForecastUiModel) {
    Column {
        Text(
            textAlign = TextAlign.Center,
            text = "Warsaw",
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )
        Text(
            textAlign = TextAlign.Center,
            text = forecast.dayName,
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
            modifier = Modifier
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 64.dp, start = 16.dp, end = 16.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.5f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${forecast.temperature}°C",
                    style = TextStyle(fontSize = 32.sp)
                )
                Text(text = "Feels like ${forecast.feelsLike}°C")
            }
            Image(
                painter = painterResource(id = R.drawable.ic_weather_stub),
                contentDescription = null,
                modifier = Modifier
                    .weight(0.5f)
                    .size(64.dp)
            )
        }

        OtherMeasurements(forecast)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sunrise),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                )
                Text(
                    text = "Sunrise at ${forecast.sunrise}",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_sunset),
                    contentDescription = null,
                    alignment = Alignment.TopCenter
                )
                Text(
                    text = "Sunset at ${forecast.sunset}",
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp)
                )
            }
        }
    }
}

@Composable
fun OtherMeasurements(forecast: DayForecastUiModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.33f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_humidity),
                contentDescription = null
            )
            Text(text = "${forecast.humidity}%", Modifier.padding(top = 4.dp))
            Text(text = "Humidity", color = Color.Gray)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.33f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pressure),
                contentDescription = null
            )
            Text(text = "${forecast.pressure}", Modifier.padding(top = 4.dp))
            Text(text = "Pressure", color = Color.Gray)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(0.33f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_wind),
                contentDescription = null
            )
            Text(
                text = forecast.windSpeed,
                Modifier.padding(top = 4.dp)
            )
            Text(text = "Wind", color = Color.Gray)
        }
    }
}