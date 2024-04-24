package io.climatecheck.app.domain.schema

import io.climatecheck.app.data.remote.dto.weather.Temperature


data class WeatherShema(
    val lat: Double,
    val lon: Double,
    val pressure: Double,
    val humidity: Double,
    val clouds: Double,
    val current: CurrentWeather,
    val hourly: List<HourlyWeather>?,
    val daily: List<DailyWeather>?
)

data class CurrentWeather(
    val temperature: Double,
    val weatherDescription: WeatherDescription
)

data class HourlyWeather(
    val temperature: Double,
    val dt: String ,
    val weatherDescription: WeatherDescription
)

data class DailyWeather(
    val temperature: Temperature,
    val dt: String,
    val weatherDescription: WeatherDescription
)

data class WeatherDescription(
    val main: String,
    val description: String,
    val icon: String
)

