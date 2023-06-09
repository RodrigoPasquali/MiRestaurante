package com.example.fragmentos.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OneFragmentViewModel: ViewModel() {
    private var _color = MutableLiveData(0)
    val color: LiveData<Int> = _color

    fun setColor(chosenColor: Int) {
        _color.value = chosenColor
    }
}