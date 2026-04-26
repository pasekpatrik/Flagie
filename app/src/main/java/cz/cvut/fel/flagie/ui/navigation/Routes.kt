package cz.cvut.fel.flagie.ui.navigation

import kotlinx.serialization.Serializable;
import cz.cvut.fel.flagie.R

@Serializable
data object GameScreen

@Serializable
data object UserScreen

@Serializable
data object StudyScreen

@Serializable
data object SettingScreen

@Serializable
data class CountryDetail(val name: String)

@Serializable
data object LoginScreen

data class NavBarItem(
    val route: Any,
    val label: String,
    val icon: Int,
)

val mainNavBarItems = listOf(
    NavBarItem(GameScreen,     "Game",     R.drawable.gamepad_24),
    NavBarItem(StudyScreen, "Study", R.drawable.book_24),
    NavBarItem(UserScreen,     "User",   R.drawable.person_24 ),
    NavBarItem(SettingScreen, "Setting", R.drawable.settings_24)
)