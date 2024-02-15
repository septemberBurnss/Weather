package nikita.awraimow.weather.ui.locations

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import nikita.awraimow.weather.ui.theme.WeatherTheme

@Composable
fun LocationsScreen(
    navController: NavController, viewModel: LocationsViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.loadSavedLocations()
    }
    val screenState = viewModel.uiState.collectAsState().value
    WeatherTheme {
        when {
            screenState is LocationsScreenState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            screenState is LocationsScreenState.NoLocations -> {
                var city by remember { mutableStateOf("") }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val modifier = Modifier.padding(8.dp)
                    Text(
                        text = "You have not added any locations yet...",
                        modifier = modifier
                    )
                    TextField(
                        value = city,
                        onValueChange = { city = it },
                        modifier = modifier
                    )
                    Button(
                        onClick = { viewModel.addCity(city) },
                        modifier = modifier
                    ) {
                        Text(text = "Add")
                    }
                }
            }
            screenState is LocationsScreenState.Loaded -> {
                Locations(screenState = screenState,
                    {
                        navController.navigate("Forecast/${it.latitude}/${it.longitude}")
                    }, viewModel::addCity
                )
            }
        }
    }
}

@Composable
fun Locations(
    screenState: LocationsScreenState.Loaded,
    onLocationClick: (LocationModel) -> Unit,
    addLocationListener: (String) -> Unit
) {
    var city by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = "Your locations",
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        )

        LazyColumn {
            items(screenState.locations) {
                LocationItem(location = it, onLocationClick = onLocationClick)
            }
        }

        TextField(
            value = city,
            onValueChange = { city = it },
        )
        Button(onClick = { addLocationListener.invoke(city) }) {
            Text(text = "Add")
        }
    }
}

@Composable
fun LocationItem(location: LocationModel, onLocationClick: (LocationModel) -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                onLocationClick.invoke(location)
            }
            .padding(8.dp)
            .border(
                shape = RoundedCornerShape(25.dp),
                width = 2.dp,
                color = Color.Black
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = location.name)
            Text(text = " (${location.country}) ")
        }
    }
}