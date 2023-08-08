package com.example.mirestaurante.ui.platos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.action.GetProducts
import com.example.mirestaurante.domain.repository.ProductRepository
import com.example.mirestaurante.infraestructure.remote.product.ProductResult
import com.example.mirestaurante.ui.product.ProductCategory
import com.example.mirestaurante.ui.product.ProductsStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlatosViewModel(
    private val repository: ProductRepository,
    private val getProducts: GetProducts
) : ViewModel() {
    private var _productStatus = MutableLiveData<ProductsStatus>()
    var productStatus: LiveData<ProductsStatus> = _productStatus

    fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            loading()
            Thread.sleep(3000)

            searchProducts()

            when ((1..10).random()) {
                in READY_PRODUCTS -> {
                    onReadyProducts()
                }

                in CONNECTION_ERROR -> {
                    onConnectionError()
                }

                in INTERNAL_ERROR -> {
                    onInternalError()
                }
            }
        }
    }

    private fun searchProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.searchProducts(ProductCategory.PLATO)
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
        getProducts(ProductCategory.PLATO).collect {
            _productStatus.postValue(
                ProductsStatus.ReadyProducts(it)
            )
        }
    }

    private fun onConnectionError() {
        _productStatus.postValue(ProductsStatus.Error("Problemas con la conexion de internet"))
    }

    private fun onInternalError() {
        _productStatus.postValue(ProductsStatus.Error("Problemas internos, disculpe las molestas"))
    }

    private fun loading() {
        _productStatus.postValue(ProductsStatus.Loading)
    }

    private fun onError() {
        _productStatus.postValue(ProductsStatus.Error("Hubo problemas con la conexion, intente de nuevo"))
    }

    private companion object {
        val READY_PRODUCTS = (1..6)
        val CONNECTION_ERROR = (7..8)
        val INTERNAL_ERROR = (9..10)
    }
}
