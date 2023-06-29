package com.example.mirestaurante.ui.bebidas

import ProductRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mirestaurante.databinding.FragmentBebidasBinding
import com.example.mirestaurante.model.Product

class BebidasFragment : Fragment() {
    private lateinit var binding: FragmentBebidasBinding
    private lateinit var adapter: ProductRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBebidasBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val products = loadProducts()
        setupAdapter(products)
    }

    private fun loadProducts(): List<Product> {
        return listOf(
            Product(1001, "Coca-Cola 2L", "Gaseosa", 1000, "image1.jpg"),
            Product(1002, "Sprite 2L", "Gaseosa", 1000, "image2.jpg"),
            Product(1003, "Aquarios Naranja 1.5L", "Jugo", 850, "image3.jpg"),
            Product(1004, "Aquarios Pomelo 1.5L", "Jugo", 850, "image3.jpg"),
            Product(1005, "Villavicencio 1.5L", "Agua", 500, "image4.jpg"),
            Product(1006, "Trapiche 750ml", "Vino", 7600, "image5.jpg"),
            Product(1007, "Benjamin 750ml", "Vino", 2800, "image6.jpg"),
            Product(1008, "Heineken(lata) 710ml", "Cerveza", 700, "image6.jpg"),
            Product(1009, "Heineken 1L", "Cerveza", 1200, "image6.jpg")
        )
    }

    private fun setupAdapter(products: List<Product>) {
        adapter = ProductRecyclerViewAdapter(products)
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)
    }
}