package com.example.mirestaurante.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.FragmentMenuBinding
import com.example.mirestaurante.domain.model.ProductCategory

class HomeMenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        onBebidasButtonClick()
        onPlatosButtonClick()

        return binding.root
    }

    private fun onBebidasButtonClick() {
        binding.bebidas.setOnClickListener {
            goToProductCategoryFragment(ProductCategory.BEBIDA)
        }
    }

    private fun onPlatosButtonClick() {
        binding.platos.setOnClickListener {
            goToProductCategoryFragment(ProductCategory.PLATO)
        }
    }

    private fun goToProductCategoryFragment(category: ProductCategory) {
        val bundle: Bundle = bundleOf("product_category" to category)

        this.findNavController().navigate(R.id.nav_products, bundle)
    }
}