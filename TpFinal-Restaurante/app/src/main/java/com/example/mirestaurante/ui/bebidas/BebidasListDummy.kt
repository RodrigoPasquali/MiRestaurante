package com.example.mirestaurante.ui.bebidas

import com.example.mirestaurante.R
import com.example.mirestaurante.domain.Product

object BebidasListDummy {
    fun getBebidas(): MutableList<Product> {
        return mutableListOf(
            Product(1001, "Coca-Cola 2L", "Gaseosa", 1000, R.drawable.botella_coca),
            Product(1002, "Sprite 2L", "Gaseosa", 1000, R.drawable.no_photo),
            Product(1003, "Aquarios Naranja 1.5L", "Jugo", 850, null),
            Product(1004, "Aquarios Pomelo 1.5L", "Jugo", 850, null),
            Product(1005, "Villavicencio 1.5L", "Agua", 500, null),
            Product(1006, "Trapiche 750ml", "Vino", 7600, null),
            Product(1007, "Benjamin 750ml", "Vino", 2800, null),
            Product(1008, "Heineken(lata) 710ml", "Cerveza", 700, null),
            Product(1009, "Heineken 1L", "Cerveza", 1200, null)
        )
    }
}