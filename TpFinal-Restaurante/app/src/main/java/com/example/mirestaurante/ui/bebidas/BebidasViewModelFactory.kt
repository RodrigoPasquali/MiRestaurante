package com.example.mirestaurante.ui.bebidas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.infraestructure.database.AppDataBase

class BebidasViewModelFactory(
    private val appDataBase: AppDataBase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BebidasViewModel(appDataBase) as T
    }
}