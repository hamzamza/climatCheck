package io.climatecheck.app.data.remote.api
import io.climatecheck.app.data.remote.dto.weather.WeatherDataDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("onecall")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): WeatherDataDto
}