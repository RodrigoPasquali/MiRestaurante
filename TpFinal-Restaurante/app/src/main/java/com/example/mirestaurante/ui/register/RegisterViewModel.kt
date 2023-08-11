package com.example.mirestaurante.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.action.user.RegisterUser
import com.example.mirestaurante.domain.model.user.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerUser: RegisterUser
) : ViewModel() {
    private var _registerStatus = MutableLiveData<RegisterStatus>()
    var registerStatus: LiveData<RegisterStatus> = _registerStatus

    fun tryRegisterUser(user: User) {
        _registerStatus.postValue(RegisterStatus.Loading)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = registerUser(user)

                if (response?.isSuccessful == true) {
                    onSuccessfulRegistration()
                } else {
                    onFailureRegistration(response?.message())
                }
            } catch (e: Exception) {
                onFailureRegistration(e.message)
            }
        }
    }

    private fun onFailureRegistration(message: String?) {
        _registerStatus.postValue(RegisterStatus.FailedRegistration(message))
    }

    private fun onSuccessfulRegistration() {
        _registerStatus.postValue(RegisterStatus.SuccessfulRegistration)
    }
}