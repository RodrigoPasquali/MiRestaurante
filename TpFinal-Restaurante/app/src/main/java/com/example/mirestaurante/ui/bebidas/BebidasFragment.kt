package com.example.mirestaurante.ui.bebidas

import ProductRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mirestaurante.databinding.FragmentBebidasBinding
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BebidasFragment : Fragment() {
    private lateinit var binding: FragmentBebidasBinding
    private lateinit var adapter: ProductRecyclerViewAdapter
    private val appBase by lazy { activity?.let { AppDataBase.getInstance(it) } }

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

        updateState(ProductListState.Loading)
        lifecycleScope.launch(Dispatchers.IO) {
            Thread.sleep(2000)
            val products = loadProducts()
            activity?.runOnUiThread {
                setupAdapter(products)
            }
        }
    }

    private fun loadProducts(): MutableList<Product> {
        return appBase?.getProductDao()?.getProducts()!!
    }

    private fun setupAdapter(products: MutableList<Product>) {
        adapter = ProductRecyclerViewAdapter(products) { product ->
            val productName = product.name
            Toast.makeText(context, "Selecciono $productName", Toast.LENGTH_SHORT).show()
        }
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)
        updateState(ProductListState.ReadyProducts)
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
        object ReadyProducts : ProductListState()
        class EmptyProducts(val message: String) : ProductListState()
        object Loading : ProductListState()
    }
}