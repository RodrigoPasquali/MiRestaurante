package com.example.mirestaurante.ui.bebidas

import ProductRecyclerViewAdapter
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mirestaurante.R
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
        updateState(ProductListState.Loading)
        Handler().postDelayed ({
            setupAdapter(products)
        }, 3000)
    }

    private fun loadProducts(): List<Product> {
        return listOf(
            Product(1001, "Coca-Cola 2L", "Gaseosa", 1000, R.drawable.botella_coca),
            Product(1002, "Sprite 2L", "Gaseosa", 1000, R.drawable.no_photo),
            Product(1003, "Aquarios Naranja 1.5L", "Jugo", 850, null),
            Product(1004, "Aquarios Pomelo 1.5L", "Jugo", 850, null),
            Product(1005, "Villavicencio 1.5L", "Agua", 500, null),
            Product(1006, "Trapiche 750ml", "Vino", 7600, null),
            Product(1007, "Benjamin 750ml", "Vino", 2800, null),
            Product(1008, "Heineken(lata) 710ml", "Cerveza", 700, null),
            Product(1009, "Heineken 1L", "Cerveza", 1200, null)
        )
    }

    private fun setupAdapter(products: List<Product>) {
        adapter = ProductRecyclerViewAdapter(products) {product ->
            val productName = product.name
            Toast.makeText(context, "Selecciono $productName", Toast.LENGTH_SHORT).show()
        }
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)
        updateState(ProductListState.ReadyProducts())
    }
    
    private fun updateState(state: ProductListState) {
        when(state) {
            is ProductListState.Loading -> {
                binding.loadingBar.visibility = View.VISIBLE
                binding.list.visibility = View.GONE
            }
            is ProductListState.ReadyProducts -> {
                binding.loadingBar.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
            }
            is ProductListState.EmptyProducts -> {}
        }
    }

    private sealed class ProductListState {
        class ReadyProducts() : ProductListState()
        class EmptyProducts(val message: String) : ProductListState()
        object Loading : ProductListState()
    }
}