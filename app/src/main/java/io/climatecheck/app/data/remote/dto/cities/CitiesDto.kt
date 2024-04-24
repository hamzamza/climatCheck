package io.climatecheck.app.data.remote.dto.cities

data class CitiesDto (
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    val population: Int,
    val isCapital: Boolean
)