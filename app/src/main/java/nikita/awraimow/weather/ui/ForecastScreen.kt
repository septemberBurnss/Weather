package nikita.awraimow.weather.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nikita.awraimow.weather.R
import nikita.awraimow.weather.ui.model.DailyForecastUiItem
import nikita.awraimow.weather.ui.navigation.Destination

@Composable
fun ForecastScreen(navController: NavController) {
    Column {
        DailyForecastItem(item = DailyForecastUiItem(
            "Monday",
            0,
            "2",
            "-5"
        )) {
            navController.navigate(Destination.ForecastDetails.route)
        }
        DailyForecastItem(item = DailyForecastUiItem(
            "Tuesday",
            0,
            "4",
            "-3"
        )) {
            navController.navigate(Destination.ForecastDetails.route)
        }
    }
}

@Composable
fun DailyForecastItem(item: DailyForecastUiItem, onDaySelected: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onDaySelected()
            }
            .padding(24.dp)
    ) {
        Text(
            text = item.day,
            modifier = Modifier.weight(0.3f)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_weather_stub),
            contentDescription = null,
            modifier = Modifier.size(16.dp).weight(0.4f)
        )
        Text(
            text = item.maxTemperature,
            color = Color.Black,
            modifier = Modifier.weight(0.15f)
        )
        Text(
            text = item.minTemperature,
            color = Color.Gray,
            modifier = Modifier.weight(0.15f)
        )
    }
}
