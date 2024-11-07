package com.example.savr.ui.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.savr.data.database.AppDatabase
import com.example.savr.data.database.dao.UserDao
import com.example.savr.data.database.model.User
import com.example.savr.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserDao, private val userRepository: UserRepository) :
    ViewModel() {
    fun login(
        email: String, password: String, onLoginSuccess: () -> Unit, onLoginFailure: () -> Unit
    ) {
        viewModelScope.launch {
            val user = userDao.getUserByEmailAndPassword(email, password).firstOrNull()
            if (user != null && user.password == password) {
                onLoginSuccess()
            } else {
                onLoginFailure()
            }
        }
    }

    private val _userName = mutableStateOf("User") // Default value
    val userName: State<String> = _userName

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    // Method to login and fetch the logged-in user
    fun fetchUser(email: String, password: String) {
        viewModelScope.launch {
            val fetchedUser =
                userRepository.getUserByEmailAndPassword(email, password).firstOrNull()
            _user.value = fetchedUser
            _userName.value = fetchedUser?.name ?: "User"
        }
    }
}

class LoginViewModelFactory(
    private val appDatabase: AppDatabase, private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return LoginViewModel(
                appDatabase.userDao(), userRepository
            ) as T // Get UserDao from AppDatabase
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}