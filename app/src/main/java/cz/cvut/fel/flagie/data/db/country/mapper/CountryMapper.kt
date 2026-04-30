package cz.cvut.fel.flagie.data.db.country.mapper

import cz.cvut.fel.flagie.data.db.country.CountryEntity
import cz.cvut.fel.flagie.data.model.Country

fun Country.toEntity() = CountryEntity(
    name = name,
    capital = capital,
    flags = flags,
    population = population,
    region = region
)

fun CountryEntity.toModel() = Country(
    name = name,
    capital = capital,
    flags = flags,
    population = population,
    region = region
)