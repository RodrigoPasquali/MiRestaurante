package com.example.mirestaurante.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mirestaurante.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

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
            if (binding.rememberUser.isChecked) {
                saveUserLogin()
            } else {
                clearUserLogin()
            }
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

    private companion object {
        const val LOGIN_PREFERENCES_KEY = "login_preference_key"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }
}