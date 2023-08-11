package com.example.mirestaurante.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.action.user.Login
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.domain.model.user.LoginUser
import com.example.mirestaurante.infraestructure.remote.user.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(
    private val sharedPreferences: EncryptedSharedPreferencesManager,
    private val login: Login
) : ViewModel() {
    private var _loginUser = MutableLiveData<LoginUser>()
    var loginUser: LiveData<LoginUser> = _loginUser

    private var _loginStatus = MutableLiveData<LoginStatus>()
    var loginStatus: LiveData<LoginStatus> = _loginStatus

    fun getLoginUser(context: Context) {
        _loginUser.postValue(sharedPreferences.getLoginUser(context))
    }

    fun onTryToLogin(loginUser: LoginUser, context: Context) {
        _loginStatus.postValue(LoginStatus.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = onLogin(
                    LoginUser(
                        loginUser.login,
                        loginUser.password
                    )
                )
                if (response?.isSuccessful == true) {
                    onSuccessfulLogin(loginUser, context)
                } else {
                    onFailureLogin()
                }
            } catch (e: Exception) {
                onFailureLogin()
            }
        }
    }

    private suspend fun onLogin(loginUser: LoginUser): Response<UserResponse>? {
        return login(loginUser)
    }

    private fun onSuccessfulLogin(loginUser: LoginUser, context: Context) {
        rememberUser(loginUser, context)
        _loginStatus.postValue(LoginStatus.SuccessfulLogin)
    }

    private fun onFailureLogin() {
        _loginStatus.postValue(LoginStatus.FailedLogin)
    }

    private fun rememberUser(loginUser: LoginUser, context: Context) {
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
}