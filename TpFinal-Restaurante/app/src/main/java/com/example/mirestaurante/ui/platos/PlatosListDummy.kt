package com.example.mirestaurante.ui.platos

import com.example.mirestaurante.R
import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.ui.product.ProductCategory

object PlatosListDummy {
    fun getPlatos(): MutableList<Product> {
        return mutableListOf(
            Product(2001, ProductCategory.PLATO, "Tallarines caseros", "Pastas", 1000, R.drawable.tallarines),
            Product(2002, ProductCategory.PLATO, "Ñuquis de papa", "¨Pastas", 1000, R.drawable.no_photo),
            Product(2003, ProductCategory.PLATO, "Canelones de verdura", "Pastas", 850, null),
            Product(2004, ProductCategory.PLATO, "Chorizo", "Carnes", 850, null),
            Product(2005, ProductCategory.PLATO, "Entraña", "Carnes", 500, null),
            Product(2006, ProductCategory.PLATO, "Pollo grille", "Carnes", 7600, null),
            Product(2007, ProductCategory.PLATO, "Pizza Muzzarella", "Pizza", 2800, null),
            Product(2008, ProductCategory.PLATO, "Pizza Napolitana", "Pizza", 700, null),
            Product(2009, ProductCategory.PLATO, "Pizza", "Cerveza", 1200, null)
        )
    }
}