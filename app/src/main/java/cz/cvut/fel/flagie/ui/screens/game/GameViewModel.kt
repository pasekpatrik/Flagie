package cz.cvut.fel.flagie.ui.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.api.CountriesApi
import cz.cvut.fel.flagie.data.model.Country
import cz.cvut.fel.flagie.domain.GameQuestion
import cz.cvut.fel.flagie.domain.GameService
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val api = CountriesApi()
    private val gameService = GameService()

    var uiState by mutableStateOf<GameQuestion?>(null)
        private set

    private var allCountries: List<Country> = emptyList()

    init {
        loadGame()
    }

    private fun loadGame() {
        viewModelScope.launch {
            try {
                if (allCountries.isEmpty()) {
                    allCountries = api.getAllCountries()
                }
                uiState = gameService.generateQuestion(allCountries)
            } catch (e: Exception) {
                // TODO
            }
        }
    }

    fun onAnswerSelected(selectedCountry: Country) {
        if (selectedCountry == uiState?.correctCountry) {
            loadGame()
        } else {
            // TODO
        }
    }
}