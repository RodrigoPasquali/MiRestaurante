package com.example.mirestaurante.infraestructure.remote.product

import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.ui.product.ProductCategory

data class ProductResult(
    var image: String? = null,
    var price: Int? = null,
    var created: Long? = null,
    var name: String? = null,
    var _Class: String? = null,
    var description: String? = null,
    var id: Long,
    var category: String? = null,
    var ownerId: String? = null,
    var updated: Long? = null,
    var objectId: String? = null
) {
    fun mapToProduct() =
        Product(
            id = id,
            category = ProductCategory.toEnum(category),
            name = name,
            description = description,
            price = price,
            image = image
        )
}
