package com.example.mirestaurante.infraestructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mirestaurante.BebidasListDummy
import com.example.mirestaurante.infraestructure.database.dao.ProductDao
import com.example.mirestaurante.infraestructure.database.dao.UserDao
import com.example.mirestaurante.model.Product
import com.example.mirestaurante.model.User
import java.util.concurrent.Executors

@Database(
    entities = [User::class, Product::class],
    version = 2,
//    autoMigrations = [AutoMigration(from = 1, to = 2)], //cuando quiera migrar y no perder la info
    exportSchema = true
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getProductDao(): ProductDao

    companion object {
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "RestauranteDB1")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute() {
                                instance?.getProductDao()?.saveProductsList(BebidasListDummy.getBebidas())
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance as AppDataBase
        }
    }
}