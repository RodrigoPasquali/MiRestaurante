package com.example.fragmentosylistas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentosylistas.databinding.ActivityMainBinding
import com.example.fragmentosylistas.ui.fragment.ProductFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var productFragment: ProductFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProductList()
    }

    private fun initProductList() {
        productFragment = ProductFragment()
        supportFragmentManager.beginTransaction().replace(binding.productListContainer.id, productFragment).commit()
    }
}