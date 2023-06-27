package com.example.mirestaurante.ui.platos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlatosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is platos Fragment"
    }
    val text: LiveData<String> = _text
}