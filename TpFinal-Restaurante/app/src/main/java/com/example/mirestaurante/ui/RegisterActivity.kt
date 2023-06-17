package com.example.mirestaurante.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mirestaurante.databinding.ActivityRegisterBinding
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val appBase by lazy { AppDataBase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onRegisterButtonClick()
    }

    private fun onRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                appBase.getUserDao().create(
                    User(
                        binding.name.text.toString(),
                        binding.lastname.text.toString(),
                        binding.streetName.text.toString(),
                        binding.streetNumber.text.toString().toInt(),
                        binding.email.text.toString(),
                        binding.password.text.toString()
                    )
                )
            }
        }
        Toast.makeText(this, "Registrado con exito", Toast.LENGTH_SHORT).show()
    }
}