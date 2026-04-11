package cz.cvut.fel.flagie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: CountryName,
    val capital: List<String>? = emptyList(),
    val flags: CountryFlags,
    val population: Int? = null,
    val region: String? = null
)

@Serializable
data class CountryName(
    val common: String,
    val official: String
)

@Serializable
data class CountryFlags(
    val png: String,
    val svg: String,
    val alt: String? = null
)