package com.example.mirestaurante.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mirestaurante.ui.product.ProductCategory

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Int,
    val category: ProductCategory,
    val name: String,
    val description: String,
    var price: Int,
    val image: Int?
)