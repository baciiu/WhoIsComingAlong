package com.example.whoiscomingalong.ui.theme.screens.loginscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    private val TAG = "LoginScreenValidation"

    fun authenticateUser(nickName: String, password: String, onResult: (Users?) -> Unit) {
        Log.d("LoginScreenViewModel", "authenticateUser called with nickName: $nickName")
        viewModelScope.launch {
            val user = usersRepository.authenticateUser(nickName, password)
            Log.d("LoginScreenViewModel", "authenticateUser result: $user")
            onResult(user)
        }
    }

    fun getUserByNickName(nickName: String, onResult: (Users?) -> Unit) {
        Log.d("LoginScreenViewModel", "getUserByNickName called with nickName: $nickName")
            viewModelScope.launch {
            val user = usersRepository.getUserByNickName(nickName)
            Log.d("LoginScreenViewModel", "getUserByNickName result: $user")
            onResult(user)
        }
    }

    private fun validateCredentials(nickName: String, password: String): Boolean {
        var isValid = true

        if (nickName.isBlank()) {
            Log.e(TAG, "Nickname is blank")
            isValid = false
        } else {
            Log.d(TAG, "Nickname is valid")
        }

        if (password.isBlank()) {
            Log.e(TAG, "Password is blank")
            isValid = false
        } else {
            Log.d(TAG, "Password is valid")
        }

        return isValid
    }

    private fun validateNickName(nickName: String): Boolean {
        return if (nickName.isBlank()) {
            Log.e(TAG, "Nickname is blank")
            false
        } else {
            Log.d(TAG, "Nickname is valid")
            true
        }
    }
}