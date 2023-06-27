package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BebidasViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bebidas Fragment"
    }
    val text: LiveData<String> = _text
}