package com.example.mirestaurante.domain.repository

import com.example.mirestaurante.domain.model.Product

interface ProductRepository {
    suspend fun insert(product: Product)
    suspend fun saveProductsList(products: MutableList<Product>)
    suspend fun getProducts(): MutableList<Product>
    suspend fun getPlatos(): MutableList<Product>
    suspend fun getBebidas(): MutableList<Product>
}