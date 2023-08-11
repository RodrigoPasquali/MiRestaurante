package com.example.mirestaurante.domain.action.product

import com.example.mirestaurante.domain.model.product.Product
import com.example.mirestaurante.domain.repository.ProductRepository

class SaveProducts(private val repository: ProductRepository) {
    suspend operator fun invoke(
        products: List<Product>
    ) = repository.saveProducts(products)
}