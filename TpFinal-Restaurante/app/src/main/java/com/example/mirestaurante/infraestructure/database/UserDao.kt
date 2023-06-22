package com.example.mirestaurante.infraestructure.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mirestaurante.model.User

@Dao
interface UserDao {
    @Insert
    fun create(user: User)

    @Query("select count(*) from user_table where email=:email and password=:password ")
    fun authenticate(email:String, password:String): Int
}