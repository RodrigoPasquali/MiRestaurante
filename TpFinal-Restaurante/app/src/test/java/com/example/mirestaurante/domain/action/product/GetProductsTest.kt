package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.model.product.Product
import com.example.mirestaurante.domain.model.product.ProductCategory
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.database.PlatosListDummy
import io.mockk.coEvery
import org.junit.Assert.*
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest

import org.junit.Before
import org.junit.Test

class GetProductsTest {
    private lateinit var getProducts: GetProducts
    private val repository = mockk<ProductRepository>()
    private var result = listOf<Product>()

    @Before
    fun setup() {
        getProducts = GetProducts(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `get product list successful`() = runTest() {
        givenProducts()

        whenGetProducts()

        thenGetProducts()
    }

    private suspend fun givenProducts() {
        coEvery { repository.getProducts(ProductCategory.PLATO) } returns flow {
            emit(PRODUCT_LIST)
        }
    }

    private suspend fun whenGetProducts() {
        result = getProducts(ProductCategory.PLATO).first()
    }

    private fun thenGetProducts() {
        assertEquals(PRODUCT_LIST, result)
    }

    private companion object {
        val PRODUCT_LIST = PlatosListDummy.getPlatos()
    }
}