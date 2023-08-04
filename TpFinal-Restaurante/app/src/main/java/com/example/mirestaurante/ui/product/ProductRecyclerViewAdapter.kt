package com.example.mirestaurante.ui.product
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mirestaurante.databinding.ItemProductBinding
import com.example.mirestaurante.domain.model.Product
import com.bumptech.glide.Glide

class ProductRecyclerViewAdapter(
    private var products: List<Product> = listOf(),
    val itemCallback: (item: Product) -> Unit
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products.get(position)
        holder.itemName.text = product.name
        holder.itemDescription.text = product.description

        val price = product.price
        holder.itemPrice.text = "$ $price"

        setProductImage(product, holder)

        holder.itemView.setOnClickListener {
            itemCallback(product)
        }
    }

    fun updateProducts(productsReceiver: List<Product>) {
        products = productsReceiver
        notifyDataSetChanged()
    }

    private fun setProductImage(product: Product, holder: ProductViewHolder) {
        Glide.with(holder.itemView)
            .load(product.image)
            .into(holder.itemImage)
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName: TextView = binding.productName
        val itemDescription: TextView = binding.productDescription
        val itemPrice: TextView = binding.productPrice
        val itemImage: ImageView = binding.productImage
    }
}