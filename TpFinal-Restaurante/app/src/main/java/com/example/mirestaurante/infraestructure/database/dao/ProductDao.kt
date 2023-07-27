package com.example.mirestaurante.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mirestaurante.domain.model.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Insert
    fun saveProductsList(products: MutableList<Product>)

    @Query("select * from product_table")
    fun getProducts(): MutableList<Product>

    @Query("select * from product_table where category = 'PLATO'")
    fun getPlatos(): MutableList<Product>

    @Query("select * from product_table where category = 'BEBIDA'")
    fun getBebidas(): MutableList<Product>
}