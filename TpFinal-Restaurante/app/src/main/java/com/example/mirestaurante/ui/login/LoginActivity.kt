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
import com.example.mirestaurante.domain.model.LoginUser
import com.example.mirestaurante.ui.NavigationDrawerActivity
import com.example.mirestaurante.ui.register.RegisterActivity

@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val progressDialog by lazy { ProgressDialog(this) }
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel =
            ViewModelProvider(
                this,
                LoginViewModelFactory(
                    Injection.provideLocalUserRepository(applicationContext),
                    Injection.provideEncryptedSharedPreferencesManager()
                )
            ).get(LoginViewModel::class.java)

        observers()
        onGetLoginUser()
        onEnterButtonClick()
        onRegisterButtonClick()
    }

    private fun observers() {
        observeLoginUser()
        observeLoginStatus()
    }

    private fun observeLoginUser() {
        loginViewModel.loginUser.observe(this) { loginUser ->
            binding.email.setText(loginUser.email)
            binding.password.setText(loginUser.password)
            binding.rememberUserCheckbox.isChecked = loginUser.remember
        }
    }

    private fun onGetLoginUser() {
        loginViewModel.getLoginUser(this)
    }

    private fun observeLoginStatus() {
        loginViewModel.loginStatus.observe(this) {
            updateLoginStatus(it)
        }
    }

    private fun updateLoginStatus(loginStatus: LoginStatus) {
        when (loginStatus) {
            LoginStatus.AuthenticatingCredentials -> {
                onAuthenticatingCredentials()
            }

            LoginStatus.SuccessfulLogin -> {
                onSuccessfulLogin()
            }

            LoginStatus.FailedLogin -> {
                onFailedLogin()
            }
        }
    }

    private fun onAuthenticatingCredentials() {
        showProgressDialog()
        disableLoginButtons()
    }

    private fun onSuccessfulLogin() {
        progressDialog.dismiss()
        enableLoginButtons()

        navigateToMenu()
    }

    private fun onFailedLogin() {
        progressDialog.dismiss()
        enableLoginButtons()

        showInvalidCredentials()
    }

    private fun showInvalidCredentials() {
        Toast.makeText(
            applicationContext,
            getString(R.string.invalid_credentials),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showProgressDialog() {
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

    private fun onEnterButtonClick() {
        binding.loginButton.setOnClickListener {
            loginViewModel.onTryToLogin(
                LoginUser(
                    binding.email.text.toString(),
                    binding.password.text.toString(),
                    binding.rememberUserCheckbox.isChecked
                ),
                this
            )
        }
    }

    private fun onRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            navigateToRegistration()
        }
    }

    private fun navigateToMenu() {
        startActivity(Intent(this, NavigationDrawerActivity::class.java))
    }

    private fun navigateToRegistration() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}