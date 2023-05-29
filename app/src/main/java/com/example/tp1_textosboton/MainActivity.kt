package com.example.tp1_textosboton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tp1_textosboton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        cuandoSePresionaBoton()
    }

    private fun mostrarDatosIngresados() {
        binding.datosIngresados.text =
            binding.nombre.text.toString() + " " + binding.apellido.text.toString()
    }

    private fun cuandoSePresionaBoton() =
        binding.boton.setOnClickListener { mostrarDatosIngresados() }
}