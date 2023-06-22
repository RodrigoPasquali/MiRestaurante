package com.example.mirestaurante.ui

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.ActivityLoginBinding
import com.example.mirestaurante.infraestructure.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var progressDialog: ProgressDialog
    private val appBase by lazy { AppDataBase.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLoginUser()
        onEnterButtonClick()
        onRegisterButtonClick()
    }

    private fun onEnterButtonClick() {
        binding.loginButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                if (isCredentialExists()) {
                    runOnUiThread {
                        showProgressDialog()
                        rememberAction()
                    }
                        Thread.sleep(5000)
                        progressDialog.dismiss()
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Login exitoso", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.apply {
            setTitle(getString(R.string.login))
            setMessage(getString(R.string.user_authenticator_message))
        }.show()
    }

    private fun isCredentialExists(): Boolean {
        return authenticateUser(
            binding.email.text.toString(),
            binding.password.text.toString()
        ) == VERIFIED_USER
    }

    private fun rememberAction() {
        if (binding.rememberUser.isChecked) {
            saveUserLogin()
        } else {
            clearUserLogin()
        }
    }

    private fun onRegisterButtonClick() {
        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun saveUserLogin() {
        val editorSharedPreferences = getSharedPreferencesLogin().edit()

        editorSharedPreferences.apply {
            putString(EMAIL_KEY, binding.email.text.toString())
            putString(PASSWORD_KEY, binding.password.text.toString())
        }.apply()
    }

    private fun getLoginUser() {
        val sharedPreferences = getSharedPreferencesLogin()

        binding.email.setText(sharedPreferences.getString(EMAIL_KEY, ""))
        binding.password.setText(sharedPreferences.getString(PASSWORD_KEY, ""))
    }

    private fun getSharedPreferencesLogin(): SharedPreferences {
        return getSharedPreferences(
            LOGIN_PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )
    }

    private fun clearUserLogin() {
        getSharedPreferencesLogin().edit().clear().apply()
    }

    private fun authenticateUser(email: String, password: String): Int {
        return appBase.getUserDao().authenticate(email, password)
    }

    private companion object {
        const val LOGIN_PREFERENCES_KEY = "login_preference_key"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
        const val VERIFIED_USER = 1
    }
}