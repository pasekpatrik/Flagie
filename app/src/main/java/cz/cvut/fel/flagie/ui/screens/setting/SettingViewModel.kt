package cz.cvut.fel.flagie.ui.screens.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.local.SettingsManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(private val settingsManager: SettingsManager) : ViewModel() {

    val isDarkMode = settingsManager.isDarkMode.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )

    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            settingsManager.setDarkMode(enabled)
        }
    }
}