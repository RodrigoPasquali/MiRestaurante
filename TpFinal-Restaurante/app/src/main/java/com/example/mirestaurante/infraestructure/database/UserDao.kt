package com.example.mirestaurante.infraestructure.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.mirestaurante.model.User

@Dao
interface UserDao {
    @Insert
    fun create(user: User)
}