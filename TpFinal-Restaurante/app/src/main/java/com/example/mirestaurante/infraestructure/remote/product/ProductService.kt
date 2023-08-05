package com.example.mirestaurante.infraestructure.remote.product

import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("api/data/product?where=category='BEBIDA'")
    suspend fun getBebidas(): Response<List<ProductResult>>

    @GET("api/data/product?where=category='PLATO'")
    suspend fun getPlatos(): Response<List<ProductResult>>
}
