package cz.cvut.fel.flagie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cz.cvut.fel.flagie.data.db.AppDatabase
import cz.cvut.fel.flagie.data.db.user.UserRepository
import cz.cvut.fel.flagie.ui.navigation.MainNavGraph
import cz.cvut.fel.flagie.ui.theme.FlagieTheme
import cz.cvut.fel.flagie.ui.screens.login.LoginViewModel
import cz.cvut.fel.flagie.data.db.country.CountryRepository
import cz.cvut.fel.flagie.ui.screens.study.StudyViewModel
import cz.cvut.fel.flagie.data.api.CountriesApi

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database by lazy { AppDatabase.getDatabase(this) }

        val userRepository by lazy { UserRepository(database.userDao()) }

        val countryRepository by lazy { CountryRepository(database.countryDao(), CountriesApi()) }

        val loginViewModel: LoginViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return LoginViewModel(userRepository) as T
                }
            }
        }

        val studyViewModel: StudyViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return StudyViewModel(countryRepository) as T
                }
            }
        }

        setContent {
            FlagieTheme {
                MainNavGraph(loginViewModel = loginViewModel, studyViewModel = studyViewModel)
            }
        }
    }
}