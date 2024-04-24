package io.climatecheck.app.data.remote.api

import io.climatecheck.app.data.remote.dto.cities.CitiesDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CitiesApi {
@GET("v1/city")
suspend fun getcitiesList(
    @Query("name")   name : String   ,
    @Query("limit")   limit : Int ,
    @Header("X-Api-Key") apiKey: String = "Al8KwEnlqDJluv6sV+b7AQ==WsR93cO4p1leCb20"
): List<CitiesDto>
}