package io.climatecheck.app.data.repository

import io.climatecheck.app.data.remote.api.CitiesApi
import io.climatecheck.app.data.remote.dto.cities.toCitySchema
import io.climatecheck.app.domain.repository.CityRepository
import io.climatecheck.app.domain.schema.CitySchema

class CityRepositoryImp(private val apiService: CitiesApi) : CityRepository {
    override suspend fun fetchCitiesByName(name: String): List<CitySchema> {
        val citiesDto = apiService.getcitiesList(name, 10)
        return citiesDto.map { it.toCitySchema()}
    }
}