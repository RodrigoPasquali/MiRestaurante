package com.example.lista.model

import java.io.Serializable

data class Pokemon(
    val name: String,
    val type: String,
    val urlImage: String
) : Serializable