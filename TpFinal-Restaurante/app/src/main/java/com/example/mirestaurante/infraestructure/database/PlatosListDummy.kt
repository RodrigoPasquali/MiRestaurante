package com.example.mirestaurante.infraestructure.database

import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.domain.model.ProductCategory

object PlatosListDummy {
    fun getPlatos(): MutableList<Product> {
        return mutableListOf(
            Product(2004, ProductCategory.PLATO, "Chorizo", "Carnes", 850, null),
            Product(2006, ProductCategory.PLATO, "Pollo grille", "Carnes", 7600, null),
        )
    }
}