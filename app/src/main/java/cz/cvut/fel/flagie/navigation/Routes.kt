package cz.cvut.fel.flagie.navigation

import kotlinx.serialization.Serializable;
import cz.cvut.fel.flagie.R

@Serializable
data object GameScreen

@Serializable
data object UserScreen

data class NavBarItem(
    val route: Any,
    val label: String,
    val icon: Int,
)

val mainNavBarItems = listOf(
    NavBarItem(GameScreen,     "Game",     R.drawable.gamepad_24),
    NavBarItem(UserScreen,     "User",   R.drawable.person_24 ),
)