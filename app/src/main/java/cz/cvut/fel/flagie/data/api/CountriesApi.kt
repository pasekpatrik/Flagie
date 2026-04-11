package cz.cvut.fel.flagie.data.api

import cz.cvut.fel.flagie.data.model.Country
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CountriesApi {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCountryByName(name: String): List<Country> {
        val response = client.get("https://restcountries.com/v3.1/name/$name")
        return response.body()
    }

    suspend fun getAllCountries(): List<Country> {
        val response = client.get("https://restcountries.com/v3.1/all?fields=name,capital,flags")
        return response.body()
    }
}