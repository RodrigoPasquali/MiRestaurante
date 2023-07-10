package com.example.mirestaurante.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.infraestructure.database.AppDataBase

class RegisterViewModelFactory(
    private val appDataBase: AppDataBase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(appDataBase) as T
    }
}