package cz.cvut.fel.flagie.ui.screens.study

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import cz.cvut.fel.flagie.data.model.Country
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.api.CountriesApi
import kotlinx.coroutines.launch

class StudyViewModel : ViewModel() {
    private val api = CountriesApi()
    var countries by mutableStateOf<List<Country>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun fetchCountries() {
        viewModelScope.launch {
            try {
                countries = api.getAllCountries()
                isLoading = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}