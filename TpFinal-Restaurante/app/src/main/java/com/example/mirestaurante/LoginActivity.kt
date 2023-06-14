package com.example.mirestaurante

import android.content.Context
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
        onButtonEnterClick()
    }

    private fun onButtonEnterClick() {
        binding.loginButton.setOnClickListener {
            saveUserLogin()
        }
    }

    private fun saveUserLogin() {
        if (binding.rememberUser.isChecked) {
            val editorSharedPreferences = getSharedPreferencesLogin().edit()

            editorSharedPreferences.apply {
                putString(EMAIL_KEY, binding.email.text.toString())
                putString(PASSWORD_KEY, binding.password.text.toString())
            }.apply()
        }
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

    private companion object {
        const val LOGIN_PREFERENCES_KEY = "login_preference_key"
        const val EMAIL_KEY = "email_key"
        const val PASSWORD_KEY = "password_key"
    }
}