package com.example.lista.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lista.databinding.ActivityMainBinding
import com.example.lista.model.Pokemon
import com.example.lista.ui.adapter.PokemonAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupListAdapter()
    }

    private fun setupListAdapter() {
        binding.list.adapter = PokemonAdapter(loadPokemonData())
        binding.list.layoutManager = LinearLayoutManager(this)
    }
    private fun loadPokemonData(): List<Pokemon> =
        listOf(
            Pokemon("Bulbasaur", "Planta", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Ivysaur", "Planta", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Venusaur", "Planta", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Charmander", "Fuego", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Charmeleon", "Fuego", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Charizard", "Fuego", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Squirtle", "Agua", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Wartortle", "Agua", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"),
            Pokemon("Blastoise", "Agua", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
        )
}