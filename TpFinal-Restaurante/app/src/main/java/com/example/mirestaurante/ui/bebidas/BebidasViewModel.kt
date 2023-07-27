package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.repository.ProductRepository
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
            Thread.sleep(3000)

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

    private suspend fun onReadyProducts() {
        _productStatus.postValue(
            ProductsStatus.ReadyProducts(
                repository.getBebidas()
            )
        )
    }

    private fun onConnectionError() {
        _productStatus.postValue(ProductsStatus.Error("Problemas con la conexion de internet"))
    }

    private fun onInternalError(){
        _productStatus.postValue(ProductsStatus.Error("Problemas internos, disculpe las molestas"))
    }

    private fun loading() {
        _productStatus.postValue(ProductsStatus.Loading)
    }

    private companion object {
        val READY_PRODUCTS =  (1..6)
        val CONNECTION_ERROR =  (7..8)
        val INTERNAL_ERROR =  (9..10)
    }
}