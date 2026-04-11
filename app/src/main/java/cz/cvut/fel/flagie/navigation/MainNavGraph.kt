package cz.cvut.fel.flagie.navigation

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

import cz.cvut.fel.flagie.ui.components.MainBottomBar
import cz.cvut.fel.flagie.ui.screens.GameScreen
import cz.cvut.fel.flagie.ui.screens.SettingScreen
import cz.cvut.fel.flagie.ui.screens.StudyScreen
import cz.cvut.fel.flagie.ui.screens.UserScreen

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    Scaffold(
       bottomBar = {
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            startDestination = GameScreen
        ) {
            composable<GameScreen> { GameScreen() }
            composable<UserScreen> { UserScreen() }
            composable<StudyScreen> { StudyScreen() }
            composable<SettingScreen> { SettingScreen() }
        }
    }
}