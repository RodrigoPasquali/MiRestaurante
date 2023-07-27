package com.example.mirestaurante.ui.platos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.repository.ProductRepository

class PlatosViewModelFactory(
    private val repository: ProductRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlatosViewModel(repository) as T
    }
}