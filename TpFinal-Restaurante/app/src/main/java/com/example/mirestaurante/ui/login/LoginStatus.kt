package com.example.mirestaurante.ui.login

sealed class LoginStatus {
    object Successful: LoginStatus()
    object Failed: LoginStatus()
    object Loading: LoginStatus()
}