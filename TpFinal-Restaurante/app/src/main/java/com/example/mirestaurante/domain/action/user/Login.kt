package com.example.mirestaurante.domain.action.user

import com.example.mirestaurante.domain.model.LoginUser
import com.example.mirestaurante.domain.repository.UserRepository

class Login(private val repository: UserRepository) {
    suspend operator fun invoke(
        loginUser: LoginUser
    ) = repository.login(loginUser)
}