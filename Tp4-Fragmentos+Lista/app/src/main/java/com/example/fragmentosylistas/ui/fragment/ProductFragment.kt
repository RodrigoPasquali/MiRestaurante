package com.example.fragmentosylistas.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentosylistas.databinding.FragmentItemListBinding
import com.example.fragmentosylistas.model.Product
import java.util.Random

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentItemListBinding
    private lateinit var adapter: ProductRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val products = loadProducts()
        setupAdapter(products)
    }

    private fun loadProducts(): List<Product> {
        return if (Random().nextBoolean()) {
            listOf()
        } else {
            listOf(
                Product("001", "Fuego"),
                Product("002", "Azul"),
                Product("003", "Amarillo"),
                Product("004", "Gold"),
                Product("005", "Silver"),
                Product("006", "Cristal")
            )
        }
    }

    private fun setupAdapter(products: List<Product>) {
        adapter = ProductRecyclerViewAdapter()
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)

        if (products.isEmpty()) {
            changeState(ProductListState.EmptyProducts("No hay productos agregados a la lista"))
        } else {
            changeState(ProductListState.ReadyProducts(products))
        }
    }

    private fun changeState(state: ProductListState) {
        when (state) {
            is ProductListState.EmptyProducts -> {
                binding.emptyProducts.visibility = View.VISIBLE
                binding.emptyProductsText.text = state.message
                binding.list.visibility = View.GONE
            }

            is ProductListState.ReadyProducts -> {
                adapter.updateProductList(state.products)
                binding.emptyProducts.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
            }
        }
    }

    private sealed class ProductListState() {
        class ReadyProducts(val products: List<Product>) : ProductListState()
        class EmptyProducts(val message: String) : ProductListState()
    }
}