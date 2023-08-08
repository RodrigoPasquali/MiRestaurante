package com.example.mirestaurante.domain.action

import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.ui.product.ProductCategory

class GetProducts(private val repository: ProductRepository) {
    suspend operator fun invoke(
        category: ProductCategory
    ) = repository.getProducts(category)
}