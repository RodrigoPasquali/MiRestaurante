package com.example.mirestaurante.di

import android.content.Context
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.infraestructure.database.AppDataBase

object Injection {
    fun provideAppDataBase(context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }

    fun provideEncryptedSharedPreferencesManager() : EncryptedSharedPreferencesManager {
        return EncryptedSharedPreferencesManager.getInstance()
    }
}