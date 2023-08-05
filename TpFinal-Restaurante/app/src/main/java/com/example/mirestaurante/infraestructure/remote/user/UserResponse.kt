package com.example.mirestaurante.infraestructure.remote.user

data class UserResponse(
    val code: Int,
    val message: String,
    val error: List<String>
)