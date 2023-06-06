package com.example.lista.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lista.R
import com.example.lista.databinding.ActivityPokemonDetailBinding
import com.example.lista.model.Pokemon

class PokemonDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPokemonDetailBinding
    private var pokemon: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        showPokemonDetails()
    }

    private fun getPokemonData() {
        val bundle = intent.extras

        pokemon = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getSerializable("pokemon", Pokemon::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle?.getSerializable("pokemon") as Pokemon?
        }
    }

    private fun showPokemonDetails() {
        getPokemonData()

        pokemon?.let {
            binding.name.text = it.name
            binding.type.text = it.type
        }
    }
}