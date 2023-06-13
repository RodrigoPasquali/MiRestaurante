package com.example.fragmentosylistas.ui.fragment

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.fragmentosylistas.databinding.FragmentItemBinding
import com.example.fragmentosylistas.model.Product

class ProductRecyclerViewAdapter(
    private val products: List<Product> = listOf()
) : RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.itemId.text = product.id
        holder.itemName.text = product.name
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemId: TextView = binding.itemId
        val itemName: TextView = binding.itemName
    }
}