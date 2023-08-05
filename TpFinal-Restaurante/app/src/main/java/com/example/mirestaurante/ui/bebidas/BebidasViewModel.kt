package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.remote.product.ProductResult
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

    private suspend fun searchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.searchProducts(ProductCategory.BEBIDA)
                if (response?.isSuccessful == true) {
                    saveProducts(response.body())
                } else {
                    onError()
                }
            } catch (e: Exception) {
                onError()
            }
        }
    }

    private suspend fun saveProducts(products: List<ProductResult>?) {
        products?.let {
            repository.saveProducts(
                it.map { productResult ->
                    productResult.mapToProduct()
                }
            )
        }
    }

    private suspend fun onReadyProducts() {
        repository.getProducts(ProductCategory.BEBIDA).collect {
            _productStatus.postValue(
                ProductsStatus.ReadyProducts(it)
            )
        }
    }

    private fun onError() {
        _productStatus.postValue(ProductsStatus.Error("Hubo problemas con la conexion, intente de nuevo"))
    }

    private fun loading() {
        _productStatus.postValue(ProductsStatus.Loading)
    }
}