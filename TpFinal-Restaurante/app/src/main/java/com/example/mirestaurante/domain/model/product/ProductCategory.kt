package com.example.mirestaurante.domain.model.product

import java.io.Serializable

enum class ProductCategory: Serializable {
    BEBIDA, PLATO, INVALID;

    companion object {
        fun toEnum(value: String?): ProductCategory = when (value) {
            "BEBIDA" -> BEBIDA
            "PLATO" -> PLATO
            else -> INVALID
        }
    }
}