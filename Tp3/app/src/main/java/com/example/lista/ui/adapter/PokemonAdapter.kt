package com.example.lista.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lista.databinding.ItemBinding
import com.example.lista.model.Pokemon

class PokemonAdapter(private val pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapterViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonAdapterViewHolder, position: Int) {
        holder.binding.name.text = pokemonList[position].name
        holder.binding.type.text = pokemonList[position].type
    }

    inner class PokemonAdapterViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}