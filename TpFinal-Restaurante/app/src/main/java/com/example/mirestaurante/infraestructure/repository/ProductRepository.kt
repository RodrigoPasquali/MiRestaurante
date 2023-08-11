package com.example.mirestaurante.infraestructure.repository

import com.example.mirestaurante.domain.model.product.Product
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.infraestructure.remote.product.ProductResult
import com.example.mirestaurante.infraestructure.remote.product.ProductService
import com.example.mirestaurante.domain.model.product.ProductCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository(
    private val service: ProductService,
    private val appDataBase: AppDataBase,
) : ProductRepository {
    override suspend fun saveProducts(products: List<Product>) {
        return withContext(Dispatchers.IO) {
            appDataBase.getProductDao().saveProducts(products)
        }
    }


    override suspend fun getProducts(category: ProductCategory): Flow<List<Product>> {
        return withContext(Dispatchers.IO) {
            appDataBase.getProductDao().getProducts(category)
        }
    }

    override suspend fun searchProducts(category: ProductCategory): Response<List<ProductResult>>? {
        return when (category) {
            ProductCategory.BEBIDA -> {
                service.getBebidas()
            }

            ProductCategory.PLATO -> {
                service.getPlatos()
            }

            else -> {
                null
            }
        }
    }
}