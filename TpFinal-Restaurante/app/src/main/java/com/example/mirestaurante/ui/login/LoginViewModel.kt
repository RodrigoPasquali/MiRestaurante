package com.example.mirestaurante.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import com.example.mirestaurante.domain.model.LoginUser
import com.example.mirestaurante.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val sharedPreferences: EncryptedSharedPreferencesManager
) : ViewModel() {
    private var _loginUser = MutableLiveData<LoginUser>()
    var loginUser: LiveData<LoginUser> = _loginUser

    private var _loginStatus = MutableLiveData<LoginStatus>()
    var loginStatus: LiveData<LoginStatus> = _loginStatus

    fun getLoginUser(context: Context) {
        _loginUser.postValue(sharedPreferences.getLoginUser(context))
    }

    fun onTryToLogin(loginUser: LoginUser, context: Context) {
        _loginStatus.postValue(LoginStatus.AuthenticatingCredentials)

        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(3000)
            if (authenticateUser(loginUser)) {
                onSuccessfulLogin(loginUser, context)
            } else {
                onFailureLogin()
            }
        }
    }

    private suspend fun authenticateUser(loginUser: LoginUser): Boolean {
        return repository.authenticate(loginUser.email, loginUser.password) == USER_EXISTS
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

    private companion object {
        const val USER_EXISTS = 1
    }
}