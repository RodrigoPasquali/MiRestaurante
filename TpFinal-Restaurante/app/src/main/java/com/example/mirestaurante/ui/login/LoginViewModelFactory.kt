package com.example.mirestaurante.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.infraestructure.database.AppDataBase

class LoginViewModelFactory(
    private val appDataBase: AppDataBase,
    private val sharedPreferences: EncryptedSharedPreferencesManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(appDataBase, sharedPreferences) as T
    }
}