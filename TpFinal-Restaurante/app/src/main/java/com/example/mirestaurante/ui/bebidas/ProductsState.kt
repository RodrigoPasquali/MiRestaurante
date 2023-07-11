package com.example.mirestaurante.ui.bebidas

import com.example.mirestaurante.model.Product

sealed class ProductsState {
    class ReadyProducts(val products: MutableList<Product>) : ProductsState()
    class EmptyProducts(val message: String) : ProductsState()
    object Loading : ProductsState()
}