package com.example.mirestaurante.ui.bebidas

import com.example.mirestaurante.domain.Product

sealed class ProductsStatus {
    class ReadyProducts(val products: MutableList<Product>) : ProductsStatus()
    class Error(val message: String) : ProductsStatus()
    object Loading : ProductsStatus()
}