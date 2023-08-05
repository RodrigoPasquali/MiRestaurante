package com.example.mirestaurante.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey val email: String,
    val password: String,
    val name: String,
    val lastname: String,
    val streetName: String,
    val streetNumber: Int,
)