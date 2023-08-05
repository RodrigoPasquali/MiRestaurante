package com.example.mirestaurante.infraestructure.remote.user

import com.example.mirestaurante.domain.model.LoginUser
import com.example.mirestaurante.domain.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("api/users/register")
    suspend fun register(@Body user: User): Response<UserResponse>?

    @Headers("Content-Type: application/json")
    @POST("api/users/login")
    suspend fun login(@Body loginUser: LoginUser): Response<UserResponse>?
}