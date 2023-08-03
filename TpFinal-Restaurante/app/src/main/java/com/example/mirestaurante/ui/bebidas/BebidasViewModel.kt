package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.ui.product.ProductCategory
import com.example.mirestaurante.ui.product.ProductsStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BebidasViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    private var _productStatus = MutableLiveData<ProductsStatus>()
    var productStatus: LiveData<ProductsStatus> = _productStatus

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            loading()
            Thread.sleep(1000)

            searchProducts()
            onReadyProducts()
        }
    }

    private fun searchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchProducts(ProductCategory.BEBIDA)
        }
    }

    private suspend fun onReadyProducts() {
        repository.getProducts(ProductCategory.BEBIDA).collect {
            _productStatus.postValue(
                ProductsStatus.ReadyProducts(it)
            )
        }
    }

    private fun loading() {
        _productStatus.postValue(ProductsStatus.Loading)
    }
}