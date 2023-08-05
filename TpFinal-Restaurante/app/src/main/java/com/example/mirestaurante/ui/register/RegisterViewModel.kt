package com.example.mirestaurante.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.domain.model.User
import com.example.mirestaurante.domain.repository.UserRepository
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
            try {
                val a = userRepository.register(user)

                if (a?.isSuccessful == true) {
                    _registerStatus.postValue(RegisterStatus.SuccessfulRegistration)
                } else {
                    _registerStatus.postValue(RegisterStatus.FailedRegistration(a?.message()))
                }
            } catch (e: Exception) {
                _registerStatus.postValue(RegisterStatus.FailedRegistration(e.message))
            }
        }
    }
}