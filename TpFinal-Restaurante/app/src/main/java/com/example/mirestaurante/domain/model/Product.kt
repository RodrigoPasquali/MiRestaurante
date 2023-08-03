package com.example.mirestaurante.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mirestaurante.ui.product.ProductCategory

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Long,
    val category: ProductCategory?,
    val name: String?,
    val description: String?,
    var price: Int?,
    val image: String?
)