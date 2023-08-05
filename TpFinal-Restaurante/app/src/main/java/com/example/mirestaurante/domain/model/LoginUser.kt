package com.example.mirestaurante.domain.model

data class LoginUser(
    val login: String,
    val password: String,
    val remember: Boolean = false
)