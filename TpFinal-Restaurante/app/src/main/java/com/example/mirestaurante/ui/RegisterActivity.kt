package com.example.mirestaurante.ui

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.ActivityRegisterBinding
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog
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
                if (areThereEmptyFields()) {
                    showEmptyFieldsMessage()
                } else {
                    runOnUiThread {
                        disableRegistrationButton()
                        showProgressDialog()
                    }
                    Thread.sleep(5000)
                    registerUser()
                    runOnUiThread {
                        progressDialog.dismiss()
                        showSuccessfulUserRegistration()
                    }
                }
            }
        }
    }

    private fun disableRegistrationButton() {
        binding.registerButton.isEnabled = false
        binding.registerButton.isClickable = false
    }
    private fun showEmptyFieldsMessage() {
        runOnUiThread {
            Toast.makeText(
                applicationContext,
                "Por favor complete todos los campos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun registerUser() {
        appBase.getUserDao().create(
            User(
                binding.name.text.toString(),
                binding.lastname.text.toString(),
                binding.streetName.text.toString(),
                getStreetNumber(binding.streetNumber.text.toString()),
                binding.email.text.toString(),
                binding.password.text.toString()
            )
        )
    }

    private fun areThereEmptyFields(): Boolean {
        return binding.name.toString().isEmpty() || binding.lastname.text.isEmpty()
                || binding.streetName.text.isEmpty() || binding.streetNumber.text.isEmpty()
                || binding.email.text.isEmpty() || binding.password.text.isEmpty()
    }

    private fun getStreetNumber(number: String): Int {
        return if (number.isNotEmpty()) {
            number.toInt()
        } else {
            0
        }
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.apply {
            setTitle(getString(R.string.user_registration))
            setMessage(getString(R.string.wait_please))
            setCancelable(false)
            setFinishOnTouchOutside(false)
        }.show()
    }

    private fun showSuccessfulUserRegistration() {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setTitle(getString(R.string.user_registration))
            setMessage(getString(R.string.successful_user_resgistration))
            setCancelable(false)
            setFinishOnTouchOutside(false)
            setPositiveButton(getString(R.string.continue_text)) { _, _ ->
                onBackPressed()
            }
        }.create().show()
    }
}