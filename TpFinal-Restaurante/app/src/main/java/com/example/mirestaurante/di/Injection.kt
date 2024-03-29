package com.example.mirestaurante.di

import android.content.Context
import com.example.mirestaurante.domain.action.product.GetProducts
import com.example.mirestaurante.domain.action.product.SaveProducts
import com.example.mirestaurante.domain.action.product.SearchProducts
import com.example.mirestaurante.domain.action.user.Login
import com.example.mirestaurante.domain.action.user.RegisterUser
import com.example.mirestaurante.domain.repository.UserRepository
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.infraestructure.remote.RetrofitClient
import com.example.mirestaurante.infraestructure.remote.product.ProductService
import com.example.mirestaurante.infraestructure.remote.user.UserService
import com.example.mirestaurante.infraestructure.repository.UserRepositoryRemote
import com.example.mirestaurante.infraestructure.repository.UserRepositoryRoom

object Injection {
    fun provideEncryptedSharedPreferencesManager() : EncryptedSharedPreferencesManager {
        return EncryptedSharedPreferencesManager.getInstance()
    }

    fun provideLocalUserRepository(context: Context): UserRepository {
        return UserRepositoryRoom(provideAppDataBase(context))
    }

    fun provideRegisterUser() = RegisterUser(provideRemoteUserRepository())

    fun provideLoginUser() = Login(provideRemoteUserRepository())

    fun provideProductRepository(context: Context): com.example.mirestaurante.domain.repository.ProductRepository {
        return com.example.mirestaurante.infraestructure.repository.ProductRepository(provideProductApiService(),provideAppDataBase(context))
    }

    fun provideGetProducts(context: Context) = GetProducts(provideProductRepository(context))

    fun provideSearchProducts(context: Context) = SearchProducts(provideProductRepository(context))

    fun provideSaveProducts(context: Context) = SaveProducts(provideProductRepository(context))

    private fun provideRemoteUserRepository(): UserRepository {
        return UserRepositoryRemote(provideUserApiService())
    }

    private fun provideProductApiService(): ProductService {
        return RetrofitClient.productApiService
    }

    private fun provideUserApiService(): UserService {
        return RetrofitClient.userApiService
    }

    private fun provideAppDataBase(context: Context): AppDataBase {
        return AppDataBase.getInstance(context)
    }
}