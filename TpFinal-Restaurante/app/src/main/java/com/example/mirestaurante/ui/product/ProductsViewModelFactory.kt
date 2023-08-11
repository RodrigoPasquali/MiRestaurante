package com.example.mirestaurante.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.domain.action.GetProducts
import com.example.mirestaurante.domain.action.SaveProducts
import com.example.mirestaurante.domain.action.SearchProducts

class ProductsViewModelFactory(
    private val getProducts: GetProducts,
    private val searchProducts: SearchProducts,
    private val saveProducts: SaveProducts
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(getProducts, searchProducts, saveProducts) as T
    }
}