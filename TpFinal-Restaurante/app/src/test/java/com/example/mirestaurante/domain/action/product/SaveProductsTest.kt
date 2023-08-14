package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.database.PlatosListDummy
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SaveProductsTest {
    private lateinit var saveProducts: SaveProducts
    private val repository = mockk<ProductRepository> (relaxed = true)

    @Before
    fun setup() {
        saveProducts = SaveProducts(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `save products successfully`() = runTest() {
        whenSaveProducts()

        thenSaveProductsSuccessfully()
    }
    
    private suspend fun whenSaveProducts() {
        saveProducts(PRODUCT_LIST)
    }

    private fun thenSaveProductsSuccessfully() {
        coVerify {
            repository.saveProducts(PRODUCT_LIST)
        }
    }

    private companion object {
        val PRODUCT_LIST = PlatosListDummy.getPlatos()
    }
}