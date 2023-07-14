package com.example.mirestaurante.ui.register

sealed class RegisterStatus {
    object SuccessfulRegistration: RegisterStatus()
    object FailedRegistration: RegisterStatus()
    object Loading: RegisterStatus()
}