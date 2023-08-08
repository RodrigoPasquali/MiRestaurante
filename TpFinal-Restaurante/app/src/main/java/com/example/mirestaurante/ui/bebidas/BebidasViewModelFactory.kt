package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.action.GetProducts
import com.example.mirestaurante.domain.repository.ProductRepository

class BebidasViewModelFactory(
    private val productRepository: ProductRepository,
    private val getProducts: GetProducts
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BebidasViewModel(productRepository, getProducts) as T
    }
}