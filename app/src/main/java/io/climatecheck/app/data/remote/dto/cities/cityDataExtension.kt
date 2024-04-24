package io.climatecheck.app.data.remote.dto.cities

import io.climatecheck.app.domain.schema.CitySchema

fun CitiesDto.toCitySchema(): CitySchema {
    return CitySchema(name, country, latitude, longitude)
}