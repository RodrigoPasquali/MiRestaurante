package com.example.mirestaurante.ui.register

sealed class RegisterStatus {
    object SuccessfulRegistration: RegisterStatus()
    class FailedRegistration(val message: String?) : RegisterStatus()
    object Loading: RegisterStatus()
}