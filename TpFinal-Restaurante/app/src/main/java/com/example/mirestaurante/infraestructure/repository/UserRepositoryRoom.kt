package com.example.mirestaurante.infraestructure.repository

import com.example.mirestaurante.domain.model.user.LoginUser
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.domain.model.user.User
import com.example.mirestaurante.domain.repository.UserRepository
import com.example.mirestaurante.infraestructure.remote.user.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepositoryRoom(database: AppDataBase) : UserRepository {
    private val userDao = database.getUserDao()

    override suspend fun register(user: User): Response<UserResponse>? {
        withContext(Dispatchers.IO) {
            userDao.create(user)
        }

        return null
    }

    override suspend fun login(loginUser: LoginUser): Response<UserResponse>? {
        withContext(Dispatchers.IO) {
            userDao.authenticate(loginUser.login, loginUser.password)
        }

        return null
    }

    suspend fun checkIfIsInDB(email: String): Int {
        return withContext(Dispatchers.IO) {
            userDao.checkIfUserIsInDB(email)
        }
    }
}