package com.example.mirestaurante.ui.login

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.ActivityLoginBinding
import com.example.mirestaurante.di.Injection
import com.example.mirestaurante.model.LoginUser
import com.example.mirestaurante.ui.NavigationDrawerActivity
import com.example.mirestaurante.ui.register.RegisterActivity

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var progressDialog: ProgressDialog
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel =
            ViewModelProvider(
                this,
                LoginViewModelFactory(
                    Injection.provideAppDataBase(applicationContext),
                    Injection.provideEncryptedSharedPreferencesManager()
                )
            ).get(LoginViewModel::class.java)

        observers()
        onGetLoginUser()
        onEnterButtonClick()
        onRegisterButtonClick()
    }

    private fun observers() {
        observeUserAuthentication()
        observeLoginUser()
    }

    private fun observeLoginUser() {
        loginViewModel.loginUser.observe(this) { loginUser ->
            binding.email.setText(loginUser.email)
            binding.password.setText(loginUser.password)
            binding.rememberUserCheckbox.isChecked = loginUser.remember
        }
    }

    private fun observeUserAuthentication() {
        loginViewModel.userAuthentication.observe(this) { credentialExists ->
            if (credentialExists) {
                onMatchingCredentialFound()
            } else {
                onMatchingCredentialNotFound()
            }
        }
    }

    private fun onGetLoginUser() {
        loginViewModel.getLoginUser(this)
    }

    private fun onEnterButtonClick() {
        binding.loginButton.setOnClickListener {
            authenticateUserCredential()
            disableLoginButtons()
            showProgressDialog()
        }
    }

    private fun onMatchingCredentialFound() {
        loginViewModel.rememberUser(
            LoginUser(
                binding.email.text.toString(),
                binding.password.text.toString(),
                binding.rememberUserCheckbox.isChecked
            ),
            this
        )

        progressDialog.dismiss()
        enableLoginButtons()

        startActivity(Intent(this, NavigationDrawerActivity::class.java))
    }


    private fun onMatchingCredentialNotFound() {
        progressDialog.dismiss()
        enableLoginButtons()

        Toast.makeText(
            applicationContext,
            "Credenciales incorrectas",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.apply {
            setTitle(getString(R.string.login))
            setMessage(getString(R.string.user_authenticator_message))
            setCancelable(false)
            setFinishOnTouchOutside(false)
        }.show()
    }

    private fun disableLoginButtons() {
        binding.registerButton.isEnabled = false
        binding.registerButton.isClickable = false
        binding.loginButton.isEnabled = false
        binding.loginButton.isClickable = false
    }

    private fun enableLoginButtons() {
        binding.registerButton.isEnabled = true
        binding.registerButton.isClickable = true
        binding.loginButton.isEnabled = true
        binding.loginButton.isClickable = true
    }

    private fun authenticateUserCredential() {
        loginViewModel.authenticateUser(
            binding.email.text.toString(),
            binding.password.text.toString()
        )
    }

    private fun onRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}