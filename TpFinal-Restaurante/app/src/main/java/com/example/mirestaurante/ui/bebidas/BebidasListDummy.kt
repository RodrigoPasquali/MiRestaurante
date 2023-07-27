package com.example.mirestaurante.ui.bebidas

import com.example.mirestaurante.R
import com.example.mirestaurante.domain.Product
import com.example.mirestaurante.ui.product.ProductCategory

object BebidasListDummy {
    fun getBebidas(): MutableList<Product> {
        return mutableListOf(
            Product(1001, ProductCategory.BEBIDA, "Coca-Cola 2L", "Gaseosa", 1000, R.drawable.botella_coca),
            Product(1002, ProductCategory.BEBIDA, "Sprite 2L", "Gaseosa", 1000, R.drawable.no_photo),
            Product(1003, ProductCategory.BEBIDA, "Aquarios Naranja 1.5L", "Jugo", 850, null),
            Product(1004, ProductCategory.BEBIDA, "Aquarios Pomelo 1.5L", "Jugo", 850, null),
            Product(1005, ProductCategory.BEBIDA, "Villavicencio 1.5L", "Agua", 500, null),
            Product(1006, ProductCategory.BEBIDA, "Trapiche 750ml", "Vino", 7600, null),
            Product(1007, ProductCategory.BEBIDA, "Benjamin 750ml", "Vino", 2800, null),
            Product(1008, ProductCategory.BEBIDA, "Heineken(lata) 710ml", "Cerveza", 700, null),
            Product(1009, ProductCategory.BEBIDA, "Heineken 1L", "Cerveza", 1200, null)
        )
    }
}