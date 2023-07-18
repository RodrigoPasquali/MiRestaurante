package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.repository.ProductRepository

class BebidasViewModelFactory(
    private val repository: ProductRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BebidasViewModel(repository) as T
    }
}