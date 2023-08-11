package com.example.mirestaurante.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.action.user.Login
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager

class LoginViewModelFactory(
    private val sharedPreferences: EncryptedSharedPreferencesManager,
    private val login: Login
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(sharedPreferences, login) as T
    }
}