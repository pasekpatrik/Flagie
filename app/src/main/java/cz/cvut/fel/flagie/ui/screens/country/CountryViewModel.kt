package cz.cvut.fel.flagie.ui.screens.country

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import cz.cvut.fel.flagie.data.model.Country
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.api.CountriesApi
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val api = CountriesApi()
    var country by mutableStateOf<List<Country>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun fetchCounty(name: String) {
        viewModelScope.launch {
            try {
                country = api.getCountryByName(name)
                isLoading = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}