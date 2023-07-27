package com.example.mirestaurante.infraestructure.repository

import com.example.mirestaurante.domain.model.Product
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

    override suspend fun getPlatos(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            productDao.getPlatos()
        }
    }

    override suspend fun getBebidas(): MutableList<Product> {
        return withContext(Dispatchers.IO) {
            productDao.getBebidas()
        }
    }
}