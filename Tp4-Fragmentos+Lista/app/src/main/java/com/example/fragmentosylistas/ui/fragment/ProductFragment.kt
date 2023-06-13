package com.example.fragmentosylistas.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentosylistas.databinding.FragmentItemListBinding
import com.example.fragmentosylistas.model.Product

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter(loadProducts())
    }

    private fun loadProducts(): List<Product> {
        return listOf(
            Product("001", "Fuego"),
            Product("002", "Azul"),
            Product("003", "Amarillo"),
            Product("004", "Gold"),
            Product("005", "Silver"),
            Product("006", "Cristal")
        )
    }

    private fun setupAdapter(products: List<Product>) {
        binding.list.adapter = ProductRecyclerViewAdapter(products)
        binding.list.layoutManager = LinearLayoutManager(activity)
    }
}