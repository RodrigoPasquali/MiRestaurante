package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.model.ProductCategory
import com.example.mirestaurante.domain.repository.ProductRepository

class SearchProducts(private val repository: ProductRepository) {
    suspend operator fun invoke(
        category: ProductCategory
    ) = repository.searchProducts(category)
}