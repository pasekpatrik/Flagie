package cz.cvut.fel.flagie.ui.screens.study

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun StudyScreen(
    viewModel: StudyViewModel = StudyViewModel()
){
    LaunchedEffect(Unit) {
        viewModel.fetchCountries()
    }

    if (!viewModel.isLoading) {
        Text("Loading...")
    } else {
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            items(viewModel.countries) { country ->
                Row {
                    Text(
                        text = country.name.common,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                    AsyncImage(
                        model = country.flags.png,
                        contentDescription = country.flags.alt,
                        modifier = Modifier
                            .size(80.dp, 50.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }
        }
    }
}
