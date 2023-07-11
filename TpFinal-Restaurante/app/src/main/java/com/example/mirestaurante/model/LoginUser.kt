package com.example.mirestaurante.model

data class LoginUser(
    val email: String?,
    val password: String?,
    val remember: Boolean
)