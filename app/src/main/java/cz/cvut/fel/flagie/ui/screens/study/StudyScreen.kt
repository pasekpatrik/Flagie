package cz.cvut.fel.flagie.ui.screens.study

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun StudyScreen(
    viewModel: StudyViewModel,
    onItemClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchCountries()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewModel.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            viewModel.errorMessage != null -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = viewModel.errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.fetchCountries() }) {
                        Text("Zkusit znovu")
                    }
                }
            }

            viewModel.countries.isEmpty() -> {
                Text(
                    text = "Žádné země k zobrazení",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
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
                                    modifier = Modifier.padding(16.dp)
                                )
                            },
                            modifier = Modifier.clickable { onItemClick(country.name.common) }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}