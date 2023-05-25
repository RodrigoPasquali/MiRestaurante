package com.example.tp1_textosboton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var datosIngresados: TextView
    private lateinit var nombre: EditText
    private lateinit var apellido: EditText
    private lateinit var boton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupVista()

        cuandoSePresionaBoton()
    }

    private fun setupVista(){
        datosIngresados = findViewById(R.id.datosIngresados)
        nombre = findViewById(R.id.nombre)
        apellido = findViewById(R.id.apellido)
        boton = findViewById(R.id.boton)
    }

    private fun mostrarDatosIngresados() {
        datosIngresados.text = nombre.text.toString() + " " + apellido.text.toString()
    }

    private fun cuandoSePresionaBoton() =
        boton.setOnClickListener { mostrarDatosIngresados() }
}