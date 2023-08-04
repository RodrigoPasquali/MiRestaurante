package com.example.mirestaurante.domain.repository

import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.infraestructure.remote.ProductResult
import com.example.mirestaurante.ui.product.ProductCategory
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRepository {
    suspend fun saveProducts(products: List<Product>)
    suspend fun getProducts(category: ProductCategory): Flow<List<Product>>
    suspend fun searchProducts(category: ProductCategory): Response<List<ProductResult>>?
}