
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.mirestaurante.databinding.ItemProductBinding

import com.example.mirestaurante.model.Product

class ProductRecyclerViewAdapter(
    private var products: List<Product> = listOf()
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
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName: TextView = binding.productName
        val itemDescription: TextView = binding.productDescription
        val itemPrice: TextView = binding.productPrice
    }
}