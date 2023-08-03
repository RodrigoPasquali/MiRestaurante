package com.example.mirestaurante.domain.repository

import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.ui.product.ProductCategory
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(category: ProductCategory): Flow<List<Product>>
    suspend fun searchProducts(category: ProductCategory)
}