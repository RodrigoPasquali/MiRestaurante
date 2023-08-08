package com.example.mirestaurante.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mirestaurante.databinding.FragmentProductsBinding
import com.example.mirestaurante.di.Injection
import com.example.mirestaurante.domain.model.Product
import com.example.mirestaurante.domain.model.ProductCategory

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductsBinding
    private lateinit var adapter: ProductRecyclerViewAdapter
    private lateinit var productsViewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductsBinding.inflate(inflater, container, false)

        productsViewModel =
            ViewModelProvider(
                this,
                ProductsViewModelFactory(
                    Injection.provideProductRepository(requireActivity().applicationContext),
                    Injection.provideGetProducts(requireActivity().applicationContext)
                )
            ).get(ProductsViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        if(arguments != null) {
            val category: ProductCategory = requireArguments().getSerializable("product_category") as ProductCategory
            productsViewModel.loadProducts(category)
        }
        setupAdapter()
    }

    private fun observers() {
        productsViewModel.productStatus.observe(viewLifecycleOwner) {
            updateStatus(it)
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

    private fun updateStatus(status: ProductsStatus) {
        when (status) {
            is ProductsStatus.Loading -> {
                onLoading()
            }

            is ProductsStatus.ReadyProducts -> {
                onReadyProducts(status.products)
            }

            is ProductsStatus.Error -> {
                onError(status.message)
            }
        }
    }

    private fun onLoading() {
        binding.loadingBar.visibility = View.VISIBLE
        binding.list.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE
    }

    private fun onReadyProducts(products: List<Product>) {
        binding.list.visibility = View.VISIBLE
        adapter.updateProducts(products)
        binding.loadingBar.visibility = View.GONE
        binding.errorContainer.visibility = View.GONE
    }
    private fun onError(message: String) {
        binding.loadingBar.visibility = View.GONE
        binding.list.visibility = View.GONE
        binding.errorContainer.visibility = View.VISIBLE
        binding.errorTexto.text = message
    }
}
