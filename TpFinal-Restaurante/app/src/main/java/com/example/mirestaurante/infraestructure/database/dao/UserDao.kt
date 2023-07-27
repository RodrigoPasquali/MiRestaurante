package com.example.mirestaurante.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mirestaurante.domain.model.User

@Dao
interface UserDao {
    @Insert
    fun create(user: User)

    @Query("select count(*) from user_table where email=:email and password=:password ")
    fun authenticate(email: String, password: String): Int

    @Query("select count(*) from user_table where email=:email")
    fun checkIfUserIsInDB(email: String): Int
}