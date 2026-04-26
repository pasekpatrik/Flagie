package cz.cvut.fel.flagie.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.db.user.UserDao
import cz.cvut.fel.flagie.data.db.user.UserEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserDao) : ViewModel() {

    val users: StateFlow<List<UserEntity>> = userDao.getAllUsers()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onLoginClicked(firstName: String, lastName: String, age: Int) {
        viewModelScope.launch {
            val newUser = UserEntity(
                firstName = firstName,
                lastName = lastName,
                age = age
            )
            userDao.insertUser(newUser)
        }
    }
}