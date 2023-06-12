package com.example.fragmentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    private var _enterText = MutableLiveData("-")
    val enterText: LiveData<String> = _enterText

    fun setText(text: String) {
        _enterText.value = text
    }
}