package com.example.mirestaurante.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mirestaurante.domain.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Insert
    fun saveProductsList(products: MutableList<Product>)

    @Query("select * from product_table")
    fun getProducts(): MutableList<Product>
}