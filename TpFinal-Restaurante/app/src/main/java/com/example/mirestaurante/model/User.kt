package com.example.mirestaurante.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    val name: String,
    val lastname: String,
    val streetName: String,
    val streetNumber: Int,
    @PrimaryKey val email: String,
    val password: String
)