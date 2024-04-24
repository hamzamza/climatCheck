package io.climatecheck.app.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import io.climatecheck.app.domain.schema.CitySchema
import io.climatecheck.app.domain.schema.CurrentWeather
import io.climatecheck.app.domain.schema.DailyWeather
import io.climatecheck.app.domain.schema.HourlyWeather
import io.climatecheck.app.domain.schema.WeatherShema
import io.climatecheck.app.presentation.AppViewModel
import io.climatecheck.app.presentation.components.SearchAppBar

@SuppressLint("SuspiciousIndentation")
@Composable
fun AppScreen(){
    val viewModel = hiltViewModel<AppViewModel>()
    val weatherData by viewModel.weatherData.collectAsState()
    val loading by viewModel.loading.collectAsState( )
    val error by viewModel.error.collectAsState( )
    val isScoopForSearch by viewModel.isScoopForSearch.collectAsState()
     Column {
         SearchAppBar(viewModel)
         if(isScoopForSearch ){
         }else {
             if(!loading) {
                 weatherData?.let { WeatherScreen(it) }
             }
             else {
                 if(error == null )
                     Text(text = "loading ......")
                 else Text(text = error?:"error in some how")
             }
         }
     }
}

@Composable
fun WeatherScreen(weatherSchema: WeatherShema) {
    LazyColumn {
        item {
            WeatherItem(weatherSchema.current)
        }
        item {
            Text("Hourly Weather", modifier = Modifier.padding(16.dp))
            LazyRow {
                items( weatherSchema.hourly?: emptyList()){HourlyWeatherItem(it)}
            }
        }
        item {
            Text("Daily Weather", modifier = Modifier.padding(16.dp))
            LazyRow {
                items( weatherSchema.daily?: emptyList()){ DailyWeatherItem(it) }
            }
        }
    }
}

@Composable
fun WeatherItem(currentWeather: CurrentWeather) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .padding(bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.fillMaxWidth() , horizontalAlignment = Alignment.CenterHorizontally ) {

            AsyncImage(
                model = "https://openweathermap.org/img/wn/${currentWeather.weatherDescription.icon}@4x.png" ,
                contentDescription = "Translated description of what the image contains" ,modifier = Modifier.size(200.dp)
            )
            Card {
       Column(Modifier.padding(10.dp)) {
           Text(
               text = "${currentWeather.temperature}°C", fontSize = 30.sp ,
               modifier = Modifier.padding(8.dp)
           )

           Text(
               text = "  ${currentWeather.weatherDescription.main}", fontSize = 20.sp ,
               modifier = Modifier.padding(8.dp)
           )
       }

}        }
    }
}

@Composable
fun HourlyWeatherItem(hourlyWeather: HourlyWeather) {
    Card(
        modifier = Modifier
            .padding(1.dp)
            .padding(bottom = 8.dp)
    ) {
       Column {

           Text(
               text = hourlyWeather.dt,
               modifier = Modifier.padding(8.dp)
           )
           AsyncImage(
               model = "https://openweathermap.org/img/wn/${ hourlyWeather.weatherDescription.icon}.png",
               contentDescription = "Translated description of what the image contains", modifier = Modifier.size(40.dp)
           )
           Text(
               text = "${hourlyWeather.temperature}°C",
               modifier = Modifier.padding(8.dp)

           )
           Text(
               text = hourlyWeather.weatherDescription.main,
               modifier = Modifier.padding(8.dp)
           )
       }

    }
}

@Composable
fun DailyWeatherItem(dailyWeather: DailyWeather) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .padding(bottom = 8.dp)
    ) {

        Column {
            Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier.padding(5.dp)) {
                Text(
                    text = dailyWeather.dt,
                    modifier = Modifier.padding(8.dp)
                )
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${ dailyWeather.weatherDescription.icon}.png",modifier = Modifier.size(40.dp),
                contentDescription = "Translated description of what the image contains"
            )
           }
            Row {

                    Card (modifier = Modifier.padding(8.dp)){
                        Column {  Text(  text = "day"   )
                        Text(  text = "${dailyWeather.temperature.day}°C", modifier = Modifier.padding(8.dp)  )
                    }
                }

                    Card (modifier = Modifier.padding(8.dp)){
                        Column { Text(  text = "night", modifier = Modifier.padding(8.dp)  )
                    Text(  text = "${dailyWeather.temperature.night}°C", modifier = Modifier.padding(8.dp)  )
                }}


                Card (modifier = Modifier.padding(8.dp)){
                    Column(modifier = Modifier.padding(8.dp)) {
                    Text(  text = "max", modifier = Modifier.padding(8.dp)  )
                    Text(  text = "${dailyWeather.temperature.max}°C", modifier = Modifier.padding(8.dp)  )
                }



            Card (modifier = Modifier.padding(8.dp)){
                        Column(modifier = Modifier.padding(8.dp)) {
                    Text(  text = "min", modifier = Modifier.padding(8.dp)  )
                    Text(  text = "${dailyWeather.temperature.min}°C", modifier = Modifier.padding(8.dp)  )
                }}

            }

            Text(
                text = dailyWeather.weatherDescription.main,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}}