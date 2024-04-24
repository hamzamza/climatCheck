package io.climatecheck.app.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import io.climatecheck.app.data.remote.api.WeatherApi
import io.climatecheck.app.data.remote.dto.weather.toDomainWeatherData
import io.climatecheck.app.domain.repository.WeatherRepository
import io.climatecheck.app.domain.schema.WeatherShema

class WeatherRepositoryImpl(private val apiService: WeatherApi) : WeatherRepository {
    val WeatherApiKey = "312021b440eac9c82a55b6388cae4211"

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(latitude: Double, longitude: Double): WeatherShema {
        return apiService.getWeatherData(latitude, latitude, WeatherApiKey).toDomainWeatherData()
    }
}