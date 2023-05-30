package com.example.tp2formularioregistro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tp2formularioregistro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enviar()
    }

    private fun enviar() {
        binding.botonEnviar.setOnClickListener {
            validaciones()
        }
    }
    private fun validaciones() {
        validarNombre()
        validarEdad()
        validarSexo()
        validarTerminos()
    }

    private fun validarNombre() {
        if (binding.nombre.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.mensaje_nombre_incompleto), Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarEdad() {
        if (binding.edad.text.toString().isEmpty()) {
            generarMensaje(getString(R.string.menasaje_edad_incompleto))
        } else if(binding.edad.text.toString().toInt() < 0 || binding.edad.text.toString().toInt() > 120) {
            generarMensaje(getString(R.string.mensaje_edad_invalida))
        }
    }

    private fun validarSexo() {
        if (binding.sexo.checkedRadioButtonId == -1) {
            generarMensaje(getString(R.string.mensaje_sexo_incompleto))
        }
    }

    private fun validarTerminos() {
        if(!binding.terminos.isChecked) {
            generarMensaje(getString(R.string.debe_aceptar_terminos_y_condiciones))
        }
    }

    private fun generarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}