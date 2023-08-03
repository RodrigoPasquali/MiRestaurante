package com.example.mirestaurante.ui.product

enum class ProductCategory {
    BEBIDA, PLATO, GENERAL;

    companion object {
        fun toEnum(value: String?): ProductCategory = when (value) {
            "BEBIDA" -> BEBIDA
            "PLATO" -> PLATO
            else -> GENERAL
        }
    }
}