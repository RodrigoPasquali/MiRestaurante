package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.infraestructure.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BebidasViewModel(
    private val appDataBase: AppDataBase
) : ViewModel() {
    private var _productState = MutableLiveData<ProductsState>()
    var productState: LiveData<ProductsState> = _productState

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

    private fun onReadyProducts() {
        _productState.postValue(
            ProductsState.ReadyProducts(
                appDataBase.getProductDao().getProducts()
            )
        )
    }

    private fun onConnectionError() {
        _productState.postValue(ProductsState.Error("Problemas con la conexion de internet"))
    }

    private fun onInternalError(){
        _productState.postValue(ProductsState.Error("Problemas internos, disculpe las molestas"))
    }

    private fun loading() {
        _productState.postValue(ProductsState.Loading)
    }

    private companion object {
        val READY_PRODUCTS =  (1..6)
        val CONNECTION_ERROR =  (7..8)
        val INTERNAL_ERROR =  (9..10)
    }
}