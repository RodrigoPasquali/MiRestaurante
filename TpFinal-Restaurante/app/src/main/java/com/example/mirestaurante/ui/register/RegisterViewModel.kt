package com.example.mirestaurante.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mirestaurante.infraestructure.database.AppDataBase
import com.example.mirestaurante.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val appDataBase: AppDataBase
) : ViewModel() {
    private var _isThereAnEmptyField = MutableLiveData<Boolean>()
    var isThereAnEmptyField: LiveData<Boolean>? = _isThereAnEmptyField
    private var _isUserExists: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var isUserExists: LiveData<Boolean> = _isUserExists
    private var _registerUserSucceess: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    var registerUserSucceess: LiveData<Boolean> = _registerUserSucceess

    fun setIsThereAnEmptyField(value: Boolean) {
        _isThereAnEmptyField.value = value
    }

    fun setIsUserExists(email: String) {
        authenticateIfExistsUser(email)
    }

    private fun authenticateIfExistsUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Thread.sleep(3000)
            _isUserExists.postValue(
                appDataBase.getUserDao().checkIfUserIsInDB(email) == USER_EXISTS
            )
        }
    }

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            appDataBase.getUserDao().create(
                user
            )
        }

        _registerUserSucceess.postValue(true)
    }

    private companion object {
        const val USER_EXISTS = 1
    }
}