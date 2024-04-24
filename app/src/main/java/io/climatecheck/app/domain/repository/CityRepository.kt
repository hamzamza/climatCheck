package io.climatecheck.app.domain.repository

import io.climatecheck.app.domain.schema.CitySchema

interface CityRepository {
    suspend fun fetchCitiesByName(name: String): List<CitySchema>

}