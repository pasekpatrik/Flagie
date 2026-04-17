package cz.cvut.fel.flagie.ui.screens.study

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun StudyScreen(
    viewModel: StudyViewModel = StudyViewModel(),
    onItemClick: (String) -> Unit
){
    LaunchedEffect(Unit) {
        viewModel.fetchCountries()
    }

    if (!viewModel.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    } else {
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ){
            items(viewModel.countries) { country ->
                ListItem(
                    leadingContent = {
                        AsyncImage(
                            model = country.flags.png,
                            contentDescription = country.flags.alt,
                            modifier = Modifier
                                .size(80.dp, 50.dp)
                                .clip(RoundedCornerShape(4.dp))
                        )
                    },
                    headlineContent = {
                        Text(
                            text = country.name.common,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    },
                    modifier = Modifier.clickable { onItemClick(country.name.common) }
                )
                HorizontalDivider()
            }
        }
    }
}
