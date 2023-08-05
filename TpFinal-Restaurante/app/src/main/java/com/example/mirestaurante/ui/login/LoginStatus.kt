package com.example.mirestaurante.ui.login

sealed class LoginStatus {
    object SuccessfulLogin: LoginStatus()
    object FailedLogin: LoginStatus()
    object Loading: LoginStatus()
}