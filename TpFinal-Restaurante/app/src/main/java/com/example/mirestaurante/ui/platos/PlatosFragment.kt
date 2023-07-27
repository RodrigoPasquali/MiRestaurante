package com.example.mirestaurante.ui.platos

import com.example.mirestaurante.ui.product.ProductRecyclerViewAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mirestaurante.databinding.FragmentPlatosBinding
import com.example.mirestaurante.di.Injection
import com.example.mirestaurante.domain.Product
import com.example.mirestaurante.ui.product.ProductsStatus

class PlatosFragment : Fragment() {
    private lateinit var binding: FragmentPlatosBinding
    private lateinit var adapter: ProductRecyclerViewAdapter
    private lateinit var platosViewModel: PlatosViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlatosBinding.inflate(inflater, container, false)

        platosViewModel =
            ViewModelProvider(
                this,
                PlatosViewModelFactory(
                    Injection.provideProductRepository(requireActivity().applicationContext)
                )
            ).get(PlatosViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observers()
        platosViewModel.loadProducts()
        setupAdapter()
    }

    private fun observers() {
        platosViewModel.productStatus.observe(viewLifecycleOwner) {
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

    private fun onReadyProducts(products: MutableList<Product>) {
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