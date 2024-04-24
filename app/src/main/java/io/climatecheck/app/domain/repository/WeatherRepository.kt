package io.climatecheck.app.domain.repository

import io.climatecheck.app.domain.schema.WeatherShema

interface WeatherRepository {
    suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherShema
}

