package com.example.mirestaurante.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.User
import com.example.mirestaurante.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    private var _registerStatus = MutableLiveData<RegisterStatus>()
    var registerStatus: LiveData<RegisterStatus> = _registerStatus

    fun tryRegisterUser(user: User) {
        _registerStatus.postValue(RegisterStatus.Loading)
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(3000)
            if (!checkIfExistsUser(user.email)) {
                registerUser(user)
            } else {
                onUserExist()
            }
        }
    }

    private suspend fun checkIfExistsUser(email: String): Boolean {
        return userRepository.checkIfIsInDB(email) == USER_EXISTS
    }

    private suspend fun registerUser(user: User) {
        userRepository.create(user)

        _registerStatus.postValue(RegisterStatus.SuccessfulRegistration)
    }

    private fun onUserExist() {
        _registerStatus.postValue(RegisterStatus.FailedRegistration)
    }

    private companion object {
        const val USER_EXISTS = 1
    }
}