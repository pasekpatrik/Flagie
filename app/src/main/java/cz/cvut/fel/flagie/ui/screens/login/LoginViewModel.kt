package cz.cvut.fel.flagie.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.fel.flagie.data.db.user.UserRepository
import cz.cvut.fel.flagie.data.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    val users: StateFlow<List<User>> = userRepository.allUsers
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun onLoginClicked(firstName: String, lastName: String, age: Int) {
        viewModelScope.launch {
            userRepository.clearAll()

            val newUser = User(
                firstName = firstName,
                lastName = lastName,
                age = age
            )

            userRepository.addUser(newUser)
        }
    }

    fun updateUser(id: Long, firstName: String, lastName: String, age: Int) {
        viewModelScope.launch {
            val updatedUser = User(
                id = id,
                firstName = firstName,
                lastName = lastName,
                age = age
            )
            userRepository.updateUser(updatedUser)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }
}