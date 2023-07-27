package com.example.mirestaurante.infraestructure.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mirestaurante.ui.bebidas.BebidasListDummy
import com.example.mirestaurante.infraestructure.database.dao.ProductDao
import com.example.mirestaurante.infraestructure.database.dao.UserDao
import com.example.mirestaurante.domain.Product
import com.example.mirestaurante.domain.User
import com.example.mirestaurante.ui.platos.PlatosListDummy
import java.util.concurrent.Executors

@Database(
    entities = [User::class, Product::class],
    version = 3,
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
                instance = Room.databaseBuilder(context, AppDataBase::class.java, "RestauranteDB2")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            Executors.newSingleThreadExecutor().execute() {
                                instance?.getProductDao()?.saveProductsList(BebidasListDummy.getBebidas())
                                instance?.getProductDao()?.saveProductsList(PlatosListDummy.getPlatos())
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