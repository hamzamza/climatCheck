package io.climatecheck.app.data.remote.dto.weather

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import io.climatecheck.app.domain.schema.CurrentWeather
import io.climatecheck.app.domain.schema.DailyWeather
import io.climatecheck.app.domain.schema.HourlyWeather
import io.climatecheck.app.domain.schema.WeatherShema
import io.climatecheck.app.domain.schema.WeatherDescription
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter



@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toDomainWeatherData(): WeatherShema {
    val currentWeather = this.current
    val weather = currentWeather.weather.first()
        return WeatherShema(
            lat = this.lat,
            lon = this.lon,
            pressure = currentWeather.pressure,
            humidity = currentWeather.humidity,
            clouds = currentWeather.clouds,
            current = CurrentWeather(
                temperature = currentWeather.temp,
                weatherDescription = WeatherDescription(  weather.main  , weather.description, weather.icon)
            ),
            hourly = this.hourly?.map {
                HourlyWeather(
                    temperature = it.temp,
                    dt = parseHourlyDateTime(it.dt),
                    weatherDescription = it.weather.first().let { w ->
                        WeatherDescription( w.main, w.description,  w.icon)
                    }
                )
            },
             daily=  this.daily?.map {
                 DailyWeather(
                     dt = parseHourlyDateTime(it.dt),
                     temperature = it.temp,
                     weatherDescription = it.weather.first().let { w ->
                         WeatherDescription( w.main, w.description,  w.icon)
                     }
                 )
             }
        )
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseDailyDateTime(dt: Long): String {
    val dateTime = Instant.ofEpochSecond(dt).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("EEEE") // EEEE for full day name (e.g., Monday)
    return formatter.format(dateTime)
}

// Function to parse the `dt` field into a string representing hours for hourly data

@RequiresApi(Build.VERSION_CODES.O)
fun parseHourlyDateTime(dt: Long): String {
    val dateTime = Instant.ofEpochSecond(dt).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("HH:mm") // HH:mm for 24-hour format (e.g., 14:30)
    return formatter.format(dateTime)
}
