package cz.cvut.fel.flagie.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.fel.flagie.data.api.CountriesApi
import cz.cvut.fel.flagie.data.model.Country

@Composable
fun StudyScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val api = CountriesApi()
        var countryInfo by remember { mutableStateOf("Načítání...") }
        var load by remember { mutableStateOf(false) }
        var countries by remember { mutableStateOf<List<Country>>(emptyList()) }

        LaunchedEffect(Unit) {
            try {
                countries = api.getAllCountries()
                load = true
            } catch (e: Exception) {
                countryInfo = "Chyba: ${e.message}"
            }
        }

        if (!load) {
            Text(text = countryInfo)
        } else {
            LazyColumn (
                modifier = Modifier.fillMaxSize()
            ){
                items(countries) { country ->
                    Text(
                        text = country.name.common,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        )
                }
            }

        }

    }
}
