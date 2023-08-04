package com.example.mirestaurante.infraestructure.repository

import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.infraestructure.remote.ProductService
import com.example.mirestaurante.ui.product.ProductCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ProductRepository(
    private val service: ProductService,
    private val appDataBase: AppDataBase,
) : ProductRepository {
    override suspend fun getProducts(category: ProductCategory): Flow<List<Product>> {
        return withContext(Dispatchers.IO) {
            appDataBase.getProductDao().getProducts(category)
        }
    }

    override suspend fun searchProducts(category: ProductCategory) {
        val response = when (category) {
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

        if (response?.isSuccessful == true) {
            val products = response.body()
            products?.let {
                appDataBase.getProductDao().saveProducts(
                    it.map { productResult ->
                        productResult.mapToProduct()
                    }
                )
            }
        }
    }
}