package io.climatecheck.app.data.remote.dto.weather

data class WeatherDataDto(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
    val current: CurrentWeather,
    val minutely: List<MinutelyWeather>?,
    val hourly: List<HourlyWeather>?,
    val daily: List<DailyWeather>?
)

data class CurrentWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Double,
    val humidity: Double,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Double,
    val visibility: Double,
    val wind_speed: Double,
    val wind_deg: Int,
    val weather: List<WeatherDescription>
)

data class WeatherDescription(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MinutelyWeather(
    val dt: String ,
    val precipitation: Double
)

data class HourlyWeather(
    val dt: Long,
    val temp: Double,
    val feels_like: Double,
    val pressure: Double,
    val humidity: Double,
    val dew_point: Double,
    val uvi: Double,
    val clouds: Double,
    val visibility: Double,
    val wind_speed: Double,
    val wind_deg: Double,
    val wind_gust: Double?,
    val weather: List<WeatherDescription>,
    val pop: Double?,
    val rain: Rain?
)

data class Rain(
    val `1h`: Double
)

data class DailyWeather(
    val dt: Long,
    val sunrise: Long,
    val sunset: Long,
    val moonrise: Long,
    val moonset: Long,
    val moon_phase: Double,
    val summary: String,
    val temp: Temperature,
    val feels_like: FeelsLike,
    val pressure: Double,
    val humidity: Double,
    val dew_point: Double,
    val wind_speed: Double,
    val wind_deg: Double,
    val wind_gust: Double,
    val weather: List<WeatherDescription>,
    val clouds: Double,
    val pop: Double,
    val rain: Double,
    val uvi: Double
)

data class Temperature(
    val day: Double,
    val min: Double,
    val max: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)

data class FeelsLike(
    val day: Double,
    val night: Double,
    val eve: Double,
    val morn: Double
)
