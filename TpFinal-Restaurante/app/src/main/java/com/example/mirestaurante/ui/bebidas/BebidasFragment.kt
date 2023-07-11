package com.example.mirestaurante.ui.bebidas

import ProductRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mirestaurante.databinding.FragmentBebidasBinding
import com.example.mirestaurante.di.Injection
import com.example.mirestaurante.model.Product

class BebidasFragment : Fragment() {
    private lateinit var binding: FragmentBebidasBinding
    private lateinit var adapter: ProductRecyclerViewAdapter
    private lateinit var bebidasViewModel: BebidasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBebidasBinding.inflate(inflater, container, false)

        bebidasViewModel =
            ViewModelProvider(
                this,
                BebidasViewModelFactory(
                    Injection.provideAppDataBase(requireActivity().applicationContext),
                )
            ).get(BebidasViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        bebidasViewModel.loadProducts()
        setupAdapter()
    }

    private fun observers() {
        bebidasViewModel.productState.observe(viewLifecycleOwner) {
            updateState(it)
        }
    }

    private fun setupAdapter() {
        adapter = ProductRecyclerViewAdapter() { product ->
            val productName = product.name
            Toast.makeText(context, "Selecciono $productName", Toast.LENGTH_SHORT).show()
        }

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)
    }

    private fun updateState(state: ProductsState) {
        when (state) {
            is ProductsState.Loading -> {
                onLoading()
            }

            is ProductsState.ReadyProducts -> {
                onReadyProducts(state.products)
            }

            is ProductsState.EmptyProducts -> {}
        }
    }

    private fun onLoading() {
        binding.loadingBar.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
    }

    private fun onReadyProducts(products: MutableList<Product>) {
        adapter.updateProducts(products)
        binding.loadingBar.visibility = View.GONE
        binding.list.visibility = View.VISIBLE
    }
}