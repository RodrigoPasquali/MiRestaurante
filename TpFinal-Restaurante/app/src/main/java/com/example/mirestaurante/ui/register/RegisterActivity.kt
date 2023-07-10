package com.example.mirestaurante.ui.register

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.ActivityRegisterBinding
import com.example.mirestaurante.di.Injection
import com.example.mirestaurante.model.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel =
            ViewModelProvider(
                this,
                RegisterViewModelFactory(Injection.provideAppDataBase(applicationContext))
            ).get(RegisterViewModel::class.java)

        observers()
        onRegisterButtonClick()
    }

    private fun observers() {
        observeIsThereAnEmptyField()
        observeUserExists()
        observeUserRegisterSuccess()
    }

    private fun observeUserExists() {
        registerViewModel.isUserExists.observe(this) { userExists ->
            if (userExists) {
                onUserExists()
            } else {
                registerUser()
            }
        }
    }   
    
    private fun observeUserRegisterSuccess() {
        registerViewModel.registerUserSucceess.observe(this) { registrationSuccess ->
            if (registrationSuccess) {
                onSuccessfulRegistration()
            }
        }
    }

    private fun observeIsThereAnEmptyField() {
        registerViewModel.isThereAnEmptyField?.observe(this) { emptyFields ->
            if (emptyFields) {
                showEmptyFieldsMessage()
            } else {
                onAllFieldsComplete()
            }
        }
    }

    private fun onRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            setIsThereAnEmptyField()
        }
    }

    private fun setIsThereAnEmptyField() {
        registerViewModel.setIsThereAnEmptyField(areThereEmptyFields())
    }

    private fun onSuccessfulRegistration() {
        progressDialog.dismiss()
        showSuccessfulUserRegistrationDialog()
    }

    private fun onUserExists() {
        showUserExistsMessage()
        enableRegistrationButton()
        progressDialog.dismiss()
    }

    private fun disableRegistrationButton() {
        binding.registerButton.isEnabled = false
        binding.registerButton.isClickable = false
    }

    private fun enableRegistrationButton() {
        binding.registerButton.isEnabled = true
        binding.registerButton.isClickable = true
    }

    private fun onAllFieldsComplete(){
        registerViewModel.setIsUserExists(binding.email.text.toString())
        disableRegistrationButton()
        showProgressDialog()
    }

    private fun showEmptyFieldsMessage() {
        Toast.makeText(
            applicationContext,
            "Por favor complete todos los campos",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showUserExistsMessage() {
        Toast.makeText(
            applicationContext,
            "El mail ya se encuentra registrado en la aplicacion",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun registerUser() {
        registerViewModel.registerUser(
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

    private fun areThereEmptyFields(): Boolean {
        return binding.name.toString().isEmpty() || binding.lastname.text.isEmpty()
                || binding.streetName.text.isEmpty() || binding.streetNumber.text.isEmpty()
                || binding.email.text.isEmpty() || binding.password.text.isEmpty()
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

    private fun showSuccessfulUserRegistrationDialog() {
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