package com.example.mirestaurante.domain.repository

import com.example.mirestaurante.domain.User

interface UserRepository {
    suspend fun create(user: User)
    suspend fun checkIfIsInDB(email: String): Int
    suspend fun authenticate(email: String, password: String): Int
}