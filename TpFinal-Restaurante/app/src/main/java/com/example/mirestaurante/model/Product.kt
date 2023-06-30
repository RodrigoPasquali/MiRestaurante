package com.example.mirestaurante.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    var price: Int,
    val image: Int?
)