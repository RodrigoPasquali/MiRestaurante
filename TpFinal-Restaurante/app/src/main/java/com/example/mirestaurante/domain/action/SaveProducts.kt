package com.example.mirestaurante.domain.action

import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.domain.repository.ProductRepository

class SaveProducts(private val repository: ProductRepository) {
    suspend operator fun invoke(
        products: List<Product>
    ) = repository.saveProducts(products)
}