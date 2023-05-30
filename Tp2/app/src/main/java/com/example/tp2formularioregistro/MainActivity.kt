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
        valdidarEdad()
        valdidarSexo()
        validarTerminos()
    }

    private fun validarNombre() {
        if (binding.nombre.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.complete_su_nombre), Toast.LENGTH_SHORT).show()
        }
    }

    private fun valdidarEdad() {
        if (binding.edad.text.toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.complete_su_edad), Toast.LENGTH_SHORT).show()
        } else if(binding.edad.text.toString().toInt() < 0 || binding.edad.text.toString().toInt() > 120) {
            Toast.makeText(this, getString(R.string.coloque_una_edad_valida), Toast.LENGTH_SHORT).show()
        }
    }

    private fun valdidarSexo() {
        if (binding.sexo.checkedRadioButtonId == -1) {
            Toast.makeText(this, getString(R.string.elija_su_sexo), Toast.LENGTH_SHORT).show()
        }
    }

    private fun validarTerminos() {
        if(!binding.terminos.isChecked) {
            Toast.makeText(this, getString(R.string.debe_aceptar_terminos_y_condiciones), Toast.LENGTH_SHORT).show()
        }
    }

}