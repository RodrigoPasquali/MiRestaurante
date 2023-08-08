package com.example.mirestaurante.infraestructure.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.domain.model.ProductCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProducts(products: List<Product>)

    @Query("select * from product_table where category = :category")
    fun getProducts(category: ProductCategory): Flow<List<Product>>
}