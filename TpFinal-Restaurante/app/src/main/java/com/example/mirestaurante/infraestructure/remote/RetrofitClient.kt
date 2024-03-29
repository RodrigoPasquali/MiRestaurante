package com.example.mirestaurante.infraestructure.remote

import com.example.mirestaurante.infraestructure.remote.product.ProductService
import com.example.mirestaurante.infraestructure.remote.user.UserService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://pallyscience.backendless.app/"
    private val gson = GsonBuilder().setLenient().create()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(providerOkHttpClient())
        .build()

    val productApiService: ProductService = retrofit.create(ProductService::class.java)
    val userApiService: UserService = retrofit.create(UserService::class.java)

    private fun providerOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}