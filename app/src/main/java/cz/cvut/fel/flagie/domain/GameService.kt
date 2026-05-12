package cz.cvut.fel.flagie.domain

import cz.cvut.fel.flagie.data.model.Country

data class GameQuestion(
    val correctCountry: Country,
    val options: List<Country>
)

class GameService {
    fun generateQuestion(allCountries: List<Country>): GameQuestion {
        val options = allCountries.shuffled().take(4)
        val correctCountry = options.random()
        return GameQuestion(correctCountry, options)
    }
}