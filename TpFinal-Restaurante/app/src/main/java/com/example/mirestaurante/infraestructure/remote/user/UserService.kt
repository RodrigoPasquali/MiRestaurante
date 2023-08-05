package com.example.mirestaurante.infraestructure.remote.user

import com.example.mirestaurante.domain.model.User
import com.example.mirestaurante.infraestructure.remote.product.ProductResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
    @Headers("Content-Type: application/json")
    @POST("api/users/register")
    suspend fun register(@Body user: User): Response<UserResponse>?

    @POST("api/users/login")
    suspend fun login(): Response<List<ProductResult>>
}