package com.example.mirestaurante.infraestructure

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.mirestaurante.domain.LoginUser

class EncryptedSharedPreferencesManager {
    fun saveUserLogin(loginUser: LoginUser, context: Context) {
        val editorSharedPreferences = getSharedPreferencesLogin(context).edit()

        editorSharedPreferences.apply {
            putString(EMAIL_KEY, loginUser.email)
            putString(PASSWORD_KEY, loginUser.password)
            putBoolean(REMEMBER_KEY, loginUser.remember)
        }.apply()
    }

    fun getLoginUser(context: Context): LoginUser {
        val sharedPreferences = getSharedPreferencesLogin(context)

        return LoginUser(
            sharedPreferences.getString(EMAIL_KEY, "").toString(),
            sharedPreferences.getString(PASSWORD_KEY, "").toString(),
            sharedPreferences.getBoolean(
                REMEMBER_KEY,
                false
            )
        )
    }

    fun clearUserLogin(context: Context) {
        getSharedPreferencesLogin(context).edit().clear().apply()
    }

    private fun getSharedPreferencesLogin(context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            LOGIN_PREFERENCES_KEY,
            getKeyAliasEncryptedSharedPreferences(),
            context.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun getKeyAliasEncryptedSharedPreferences(): String {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        return MasterKeys.getOrCreate(keyGenParameterSpec)
    }

    companion object {
        private var instance: EncryptedSharedPreferencesManager? = null

        fun getInstance(): EncryptedSharedPreferencesManager =
            instance ?: synchronized(this) {
                instance ?: EncryptedSharedPreferencesManager().also { instance = it }
            }

        private const val LOGIN_PREFERENCES_KEY = "login_preference_key_v3"
        private const val EMAIL_KEY = "email_key"
        private const val PASSWORD_KEY = "password_key"
        private const val REMEMBER_KEY = "remember_key"
    }
}