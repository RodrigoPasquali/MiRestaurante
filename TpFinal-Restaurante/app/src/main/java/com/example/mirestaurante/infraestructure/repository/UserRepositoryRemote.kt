package com.example.mirestaurante.infraestructure.repository

import com.example.mirestaurante.domain.model.user.LoginUser
import com.example.mirestaurante.domain.model.user.User
import com.example.mirestaurante.domain.repository.UserRepository
import com.example.mirestaurante.infraestructure.remote.user.UserResponse
import com.example.mirestaurante.infraestructure.remote.user.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepositoryRemote(private val service: UserService) : UserRepository {
    override suspend fun register(user: User): Response<UserResponse>? {
        return withContext(Dispatchers.IO) {
            service.register(user)
        }
    }

    override suspend fun login(loginUser: LoginUser): Response<UserResponse>? {
        return withContext(Dispatchers.IO) {
            service.login(loginUser)
        }
    }
}