package cz.cvut.fel.flagie.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

import cz.cvut.fel.flagie.ui.components.MainBottomBar
import cz.cvut.fel.flagie.ui.screens.game.GameScreen
import cz.cvut.fel.flagie.ui.screens.setting.SettingScreen
import cz.cvut.fel.flagie.ui.screens.study.StudyScreen
import cz.cvut.fel.flagie.ui.screens.user.UserScreen
import cz.cvut.fel.flagie.ui.screens.country.CountryScreen
import cz.cvut.fel.flagie.ui.screens.login.*

@Composable
fun MainNavGraph(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route != LoginScreen::class.qualifiedName) {
                MainBottomBar(
                    currentDestination = currentDestination,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = LoginScreen
        ) {
            composable<LoginScreen> {
                LoginScreen(
                    viewModel = loginViewModel,
                    onLoginSuccess = {
                        navController.navigate(StudyScreen) {
                            popUpTo(LoginScreen) { inclusive = true }
                        }
                    }
                )
            }

            composable<GameScreen> { GameScreen() }
            composable<UserScreen> { UserScreen(viewModel = loginViewModel) }
            composable<StudyScreen> { StudyScreen(
                onItemClick = { name ->
                    navController.navigate(CountryDetail(name = name))
                }
            ) }

            composable<CountryDetail> { backStackEntry ->
                val detail: CountryDetail = backStackEntry.toRoute()
                CountryScreen(
                    name = detail.name,
                    onBack = { navController.navigateUp() },
                )
            }
            composable<SettingScreen> { SettingScreen() }
        }
    }
}