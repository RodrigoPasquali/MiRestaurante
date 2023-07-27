package com.example.mirestaurante.domain.model

data class LoginUser(
    val email: String,
    val password: String,
    val remember: Boolean
)