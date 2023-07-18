package com.example.mirestaurante.infraestructure.repository

import com.example.mirestaurante.domain.Product
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepositoryRoom(database: AppDataBase): ProductRepository {
    private val productDao = database.getProductDao()

    override suspend fun insert(product: Product) {
        withContext(Dispatchers.IO) {
            productDao.insert(product)
        }
    }

    override suspend fun saveProductsList(products: MutableList<Product>) {
        withContext(Dispatchers.IO) {
            productDao.saveProductsList(products)
        }
    }

    override suspend fun getProducts(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            productDao.getProducts()
        }
    }
}