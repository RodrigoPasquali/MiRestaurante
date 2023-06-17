package com.example.mirestaurante.infraestructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mirestaurante.model.User

@Database(entities = [User::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "RestauranteDB")
                    .build()
            }

            return instance as AppDataBase
        }
    }
}