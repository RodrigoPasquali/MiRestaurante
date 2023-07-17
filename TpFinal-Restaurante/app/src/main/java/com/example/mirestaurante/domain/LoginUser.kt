package com.example.mirestaurante.domain

data class LoginUser(
    val email: String,
    val password: String,
    val remember: Boolean
)