package com.example.mirestaurante.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.model.LoginUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val appDataBase: AppDataBase,
    private val sharedPreferences: EncryptedSharedPreferencesManager
) : ViewModel() {
    private var _userAuthentication = MutableLiveData<Boolean>()
    var userAuthentication: LiveData<Boolean> = _userAuthentication
    private var _loginUser = MutableLiveData<LoginUser>()
    var loginUser: LiveData<LoginUser> = _loginUser

    fun authenticateUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(3000)
            _userAuthentication.postValue(
                appDataBase.getUserDao().authenticate(email, password) == USER_EXISTS
            )
        }
    }

    fun rememberUser(loginUser: LoginUser, context: Context) {
        if (loginUser.remember) {
            saveUserLogin(loginUser, context)
        } else {
            clearUserLogin(context)
        }
    }

    private fun saveUserLogin(loginUser: LoginUser, context: Context) {
        sharedPreferences.saveUserLogin(loginUser, context)
    }

    private fun clearUserLogin(context: Context) {
        sharedPreferences.clearUserLogin(context)
    }

    fun getLoginUser(context: Context) {
        _loginUser.postValue(sharedPreferences.getLoginUser(context))
    }

    private companion object {
        const val USER_EXISTS = 1
    }
}