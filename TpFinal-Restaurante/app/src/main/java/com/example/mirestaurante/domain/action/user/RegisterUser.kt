package com.example.mirestaurante.domain.action.user

import com.example.mirestaurante.domain.model.User
import com.example.mirestaurante.domain.repository.UserRepository

class RegisterUser(private val repository: UserRepository) {
    suspend operator fun invoke(
        user: User
    ) = repository.register(user)
}