package cz.cvut.fel.flagie.data.db.country

import cz.cvut.fel.flagie.data.model.Country
import cz.cvut.fel.flagie.data.db.country.mapper.*
import cz.cvut.fel.flagie.data.api.CountriesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error(
        val exception: Throwable,
        val message: String? = null
    ) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

class CountryRepository(
    private val countryDao: CountryDao,
    private val countriesApi: CountriesApi = CountriesApi()
) {

    val allCountries: Flow<List<Country>> = countryDao.getAllCountries().map { entities ->
        entities.map { it.toModel() }
    }

    suspend fun insertCountry(country: Country) {
        countryDao.insertCountry(country.toEntity())
    }

    suspend fun insertCountries(countries: List<Country>) {
        countryDao.insertCountries(countries.map { it.toEntity() })
    }

    suspend fun getCountryById(id: Long): Country? {
        return countryDao.getCountryById(id)?.toModel()
    }

    suspend fun deleteCountry(country: Country) {
        countryDao.deleteCountry(country.toEntity())
    }

    suspend fun deleteAllCountries() {
        countryDao.deleteAllCountries()
    }

    suspend fun refreshCountries(): ApiResult<Unit> {
        return try {
            val entities = countriesApi.getAllCountries()
            this.insertCountries(entities)
            ApiResult.Success(Unit)
        } catch (e: Exception) {
            ApiResult.Error(e, "Failed to refresh from network")
        }
    }
}