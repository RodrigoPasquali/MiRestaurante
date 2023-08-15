package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.model.product.ProductCategory
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.remote.product.ProductResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SearchProductsTest {
    private lateinit var searchProducts: SearchProducts
    private val repository = mockk<ProductRepository>()
    private var result: Response<List<ProductResult>>? = null

    @Before
    fun setup() {
        searchProducts = SearchProducts(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `search product list successful`() = runTest() {
        givenProducts()

        whenSearchProducts()

        thenFoundProducts()
    }

    private suspend fun givenProducts() {
        val response: Response<List<ProductResult>>? = Response.success(PRODUCT_LIST_RESULT)
        coEvery { repository.searchProducts(ProductCategory.PLATO) } returns response
    }

    private suspend fun whenSearchProducts() {
        result = searchProducts(ProductCategory.PLATO)
    }

    private fun thenFoundProducts() {
        assertEquals(PRODUCT_LIST_RESULT, result?.body())
    }

    private companion object {
        val PRODUCT_LIST_RESULT: List<ProductResult> = listOf(
            ProductResult(
                image = "https://molti.com.ar/wp-content/uploads/2020/08/aquarius-naranja.jpg",
                price = 850,
                created = 1690439022851,
                name = "Aquarios Naranja 1.5L",
                _Class = "product",
                description = "Jugo",
                id = 1003,
                category = "BEBIDA",
                ownerId = null,
                updated = null,
                objectId = "8DBAAF7A-6078-423E-9EE2-4076CE65E9D8"
            ),
            ProductResult(
                image = "https://http2.mlstatic.com/D_NQ_NP_2X_723243-MLA70212785187_062023-F.webp",
                price = 850,
                created = 1691113181115,
                name = "Sprite 2L",
                _Class = "product",
                description = "Gaseosa",
                id = 1002,
                category = "BEBIDA",
                ownerId = null,
                updated = null,
                objectId = "978A2989-8B25-4D05-8E53-D6D62C4E5FEA"
            )
        )
    }
}