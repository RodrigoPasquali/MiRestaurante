package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.domain.model.ProductCategory

class GetProducts(private val repository: ProductRepository) {
    suspend operator fun invoke(
        category: ProductCategory
    ) = repository.getProducts(category)
}