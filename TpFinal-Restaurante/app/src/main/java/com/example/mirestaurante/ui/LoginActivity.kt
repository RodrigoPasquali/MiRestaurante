package com.example.mirestaurante.ui

import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.mirestaurante.R
import com.example.mirestaurante.databinding.ActivityLoginBinding
import com.example.mirestaurante.infraestructure.database.AppDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
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
            disableLoginButtons()
            showProgressDialog()
            lifecycleScope.launch(Dispatchers.IO) {
                Thread.sleep(5000)
                if (isCredentialExists()) {
                    onMatchingCredentialFound()
                } else {
                    onMatchingCredentialNotFound()
                }
            }
        }
    }

    private fun getSharedPreferencesLogin(): SharedPreferences{
        return EncryptedSharedPreferences.create(
            LOGIN_PREFERENCES_KEY,
            getKeyAliasEncryptedSharedPreferences(),
            applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getKeyAliasEncryptedSharedPreferences(): String {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        return MasterKeys.getOrCreate(keyGenParameterSpec)
    }

    private fun onMatchingCredentialFound() {
        runOnUiThread {
            rememberAction()
        }

        progressDialog.dismiss()
        runOnUiThread {
            enableLoginButtons()
            Toast.makeText(applicationContext, "Login exitoso", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onMatchingCredentialNotFound() {
        progressDialog.dismiss()

        runOnUiThread {
            enableLoginButtons()
            Toast.makeText(
                applicationContext,
                "Credenciales incorrectas",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(this)
        progressDialog.apply {
            setTitle(getString(R.string.login))
            setMessage(getString(R.string.user_authenticator_message))
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

    private fun isCredentialExists(): Boolean {
        return authenticateUser(
            binding.email.text.toString(),
            binding.password.text.toString()
        ) == VERIFIED_USER
    }

    private fun rememberAction() {
        if (binding.rememberUserCheckbox.isChecked) {
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
            putBoolean(REMEMBER_KEY, binding.rememberUserCheckbox.isChecked)
        }.apply()
    }

    private fun getLoginUser() {
        val sharedPreferences = getSharedPreferencesLogin()

        binding.email.setText(sharedPreferences.getString(EMAIL_KEY, ""))
        binding.password.setText(sharedPreferences.getString(PASSWORD_KEY, ""))
        binding.rememberUserCheckbox.isChecked =
            sharedPreferences.getBoolean(
                REMEMBER_KEY,
                false
            )
    }

//    private fun getSharedPreferencesLogin(): SharedPreferences {
//        return getSharedPreferences(
//            LOGIN_PREFERENCES_KEY,
//            Context.MODE_PRIVATE
//        )
//    }

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
        const val REMEMBER_KEY = "remember_key"
        const val VERIFIED_USER = 1
    }
}