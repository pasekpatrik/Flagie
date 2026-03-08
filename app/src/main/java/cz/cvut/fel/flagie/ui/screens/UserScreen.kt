package cz.cvut.fel.flagie.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.fel.flagie.ui.theme.FlagieTheme
import cz.cvut.fel.flagie.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserScreenPreview() {
    FlagieTheme {
        UserScreen()
    }
}

@Composable
fun UserScreen() {
    Scaffold (
        topBar = { UserScreenTopBar() },
        bottomBar = { UserScreenBottomBar() },
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding  -> UserScreenContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreenTopBar() {
    TopAppBar(
        title = { Text("User") }
    )
}

@Composable
fun UserScreenContent(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .absolutePadding(left = 16.dp, top = 16.dp)
    ) {

    }
}

@Composable
fun UserScreenBottomBar() {
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(R.drawable.person_24),
                    contentDescription = "User"
                )
            },
            label = { Text("User") },
            selected = true,
            onClick = {}
        )
    }
}