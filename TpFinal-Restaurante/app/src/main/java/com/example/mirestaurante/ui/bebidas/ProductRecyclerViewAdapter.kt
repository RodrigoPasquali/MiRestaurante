
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.ItemProductBinding

import com.example.mirestaurante.model.Product
import com.squareup.picasso.Picasso

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
        val product = products[position]
        holder.itemName.text = product.name
        holder.itemDescription.text = product.description

        val price = product.price
        holder.itemPrice.text = "$ $price"

        val productImage = product.image ?: R.drawable.no_photo
        Picasso.get().load(productImage).into(holder.itemImage)

        holder.itemView.setOnClickListener {
            itemCallback(product)
        }
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName: TextView = binding.productName
        val itemDescription: TextView = binding.productDescription
        val itemPrice: TextView = binding.productPrice
        val itemImage: ImageView = binding.productImage
    }
}