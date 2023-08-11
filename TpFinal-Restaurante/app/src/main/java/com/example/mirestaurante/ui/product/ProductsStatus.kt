package com.example.mirestaurante.ui.product

import com.example.mirestaurante.domain.model.product.Product

sealed class ProductsStatus {
    class ReadyProducts(val products: List<Product>) : ProductsStatus()
    class Error(val message: String) : ProductsStatus()
    object Loading : ProductsStatus()
}