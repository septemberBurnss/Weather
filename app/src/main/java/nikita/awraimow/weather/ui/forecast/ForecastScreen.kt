package nikita.awraimow.weather.ui.forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import nikita.awraimow.weather.ui.navigation.Destination
import nikita.awraimow.weather.ui.navigation.withParam

@Composable
fun ForecastScreen(
    navController: NavController,
    viewModel: ForecastViewModel
) {
    val state = viewModel.uiState.collectAsState().value
    Column {
        if (state is ForecastScreenState.Loaded) {
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
                    navController.navigate(Destination.ForecastDetails.withParam(item.date))
                }
            }
        }
    }
    viewModel.getForecast()
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
        Image(
            painter = painterResource(id = R.drawable.ic_weather_stub),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
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
