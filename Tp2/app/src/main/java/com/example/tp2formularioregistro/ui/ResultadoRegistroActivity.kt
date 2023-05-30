package com.example.tp2formularioregistro.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tp2formularioregistro.databinding.ActivityResultadoRegistroBinding
import com.example.tp2formularioregistro.modelo.FormularioDeRegistro

class ResultadoRegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultadoRegistroBinding
    private var formulario: FormularioDeRegistro? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoRegistroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mostrarDatos()
    }

    private fun obtenerFormulario() {
        val bundle = intent.extras

        formulario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getSerializable("formulario", FormularioDeRegistro::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle?.getSerializable("formulario") as FormularioDeRegistro?
        }
    }

    private fun mostrarDatos() {
        obtenerFormulario()

        formulario?.let {
            binding.nombre.text = it.nombre
            binding.edad.text = it.edad.toString()
            binding.genero.text = it.genero.toString()
        }
    }
}