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
            _productState.postValue(
                ProductsState.ReadyProducts(
                    appDataBase.getProductDao().getProducts()
                )
            )
        }
    }

    private fun loading() {
        _productState.postValue(ProductsState.Loading)
    }
}