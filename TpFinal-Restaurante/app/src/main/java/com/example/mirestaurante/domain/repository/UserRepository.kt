package com.example.mirestaurante.domain.repository

import com.example.mirestaurante.domain.model.User
import com.example.mirestaurante.infraestructure.remote.user.UserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun register(user: User): Response<UserResponse>?
    suspend fun checkIfIsInDB(email: String): Int
    suspend fun authenticate(email: String, password: String): Int
}