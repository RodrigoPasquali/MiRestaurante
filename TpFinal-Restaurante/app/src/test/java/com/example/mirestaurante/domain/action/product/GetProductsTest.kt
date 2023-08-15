package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.model.product.Product
import com.example.mirestaurante.domain.model.product.ProductCategory
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.database.PlatosListDummy
import io.mockk.coEvery
import io.mockk.coVerify
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
        givenProductsOnPlatosCategory()

        whenGetProducts(ProductCategory.PLATO)

        thenGetPlatoProducts()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should call getProducts repository`() = runTest() {
        givenProductsOnPlatosCategory()

        whenGetProducts(ProductCategory.PLATO)

        thenCallGetProductsRepository(ProductCategory.PLATO)
    }
    
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return empty list on general category`() = runTest() {
        givenNoGeneralProductsCategory()

        whenGetProducts(ProductCategory.GENERAL)

        thenCallGetProductsRepository(ProductCategory.GENERAL)
        thenReturnEmptyList()
    }

    private suspend fun givenProductsOnPlatosCategory() {
        coEvery { repository.getProducts(ProductCategory.PLATO) } returns flow {
            emit(PLATOS_PRODUCT_LIST)
        }
    }

    private suspend fun givenNoGeneralProductsCategory() {
        coEvery { repository.getProducts(ProductCategory.GENERAL) } returns flow {
            emit(emptyList())
        }
    }

    private suspend fun whenGetProducts(category: ProductCategory) {
        result = getProducts(category).first()
    }

    private fun thenGetPlatoProducts() {
        assertEquals(PLATOS_PRODUCT_LIST, result)
    }

    private fun thenReturnEmptyList() {
        assertEquals(emptyList<Product>(), result)
    }

    private fun thenCallGetProductsRepository(category: ProductCategory) {
        coVerify {
            repository.getProducts(category)
        }
    }

    private companion object {
        val PLATOS_PRODUCT_LIST = PlatosListDummy.getPlatos()
    }
}