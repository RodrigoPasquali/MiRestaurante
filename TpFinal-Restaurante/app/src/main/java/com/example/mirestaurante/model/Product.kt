package com.example.mirestaurante.model

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    var price: Int,
    val image: String
)