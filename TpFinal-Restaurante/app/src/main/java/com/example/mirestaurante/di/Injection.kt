package com.example.mirestaurante.di

import android.content.Context
import com.example.mirestaurante.domain.repository.UserRepository
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.infraestructure.repository.UserRepositoryRoom

object Injection {
    fun provideAppDataBase(context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    fun provideEncryptedSharedPreferencesManager() : EncryptedSharedPreferencesManager {
        return EncryptedSharedPreferencesManager.getInstance()
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepositoryRoom(provideAppDataBase(context))
    }
}