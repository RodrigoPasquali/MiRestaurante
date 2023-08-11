package com.example.mirestaurante.domain.repository

import com.example.mirestaurante.domain.model.user.LoginUser
import com.example.mirestaurante.domain.model.user.User
import com.example.mirestaurante.infraestructure.remote.user.UserResponse
import retrofit2.Response

interface UserRepository {
    suspend fun register(user: User): Response<UserResponse>?
    suspend fun login(loginUser: LoginUser): Response<UserResponse>?
}