package io.climatecheck.app.domain.schema

data class CitySchema(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)
