package cz.cvut.fel.flagie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cz.cvut.fel.flagie.ui.screens.UserScreen
import cz.cvut.fel.flagie.ui.theme.FlagieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlagieTheme {
                UserScreen()
            }
        }
    }
}
