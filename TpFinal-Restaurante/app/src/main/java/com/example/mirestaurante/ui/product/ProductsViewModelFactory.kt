package com.example.mirestaurante.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.action.GetProducts
import com.example.mirestaurante.domain.repository.ProductRepository

class ProductsViewModelFactory(
    private val productRepository: ProductRepository,
    private val getProducts: GetProducts
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(productRepository, getProducts) as T
    }
}