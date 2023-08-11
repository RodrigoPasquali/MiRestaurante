package com.example.mirestaurante.domain.model.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Long,
    val category: ProductCategory?,
    val name: String?,
    val description: String?,
    var price: Int?,
    val image: String?
)