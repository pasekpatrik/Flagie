package cz.cvut.fel.flagie.ui.screens.study

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.db.country.ApiResult
import cz.cvut.fel.flagie.data.db.country.CountryRepository
import cz.cvut.fel.flagie.data.model.Country
import kotlinx.coroutines.launch

class StudyViewModel(
    private val countryRepository: CountryRepository
) : ViewModel() {

    var countries by mutableStateOf<List<Country>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        observeCountries()
    }

    private fun observeCountries() {
        viewModelScope.launch {
            countryRepository.allCountries.collect { countryList ->
                countries = countryList
            }
        }
    }

    fun fetchCountries() {
        viewModelScope.launch {
            // Pokud již máme data a nenačítáme, nechceme spouštět zbytečně síťový požadavek
            if (countries.isNotEmpty()) return@launch

            isLoading = true
            errorMessage = null

            when (val result = countryRepository.refreshCountries()) {
                is ApiResult.Success -> {

                }
                is ApiResult.Error -> {
                    errorMessage = result.message ?: "Došlo k chybě při načítání dat."
                }
                ApiResult.Loading -> {

                }
            }

            isLoading = false
        }
    }
}