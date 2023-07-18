package com.example.mirestaurante.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.repository.UserRepository
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager

class LoginViewModelFactory(
    private val repository: UserRepository,
    private val sharedPreferences: EncryptedSharedPreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(repository, sharedPreferences) as T
    }
}