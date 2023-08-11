package com.example.mirestaurante.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.action.user.RegisterUser

class RegisterViewModelFactory(
    private val registerUser: RegisterUser
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RegisterViewModel(registerUser) as T
    }
}