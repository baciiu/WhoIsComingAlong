package com.example.whoiscomingalong.ui.theme.screens.loginscreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.mocks.MockUserRepository
import com.example.whoiscomingalong.mocks.MockUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: MockUserRepository
) : AndroidViewModel(application) {

    fun authenticateUser(nickName: String, password: String, onResult: (MockUsers?) -> Unit) {
        Log.d("LoginScreenViewModel", "authenticateUser called with nickName: $nickName")
        viewModelScope.launch {
            val user = usersRepository.authenticateUser(nickName, password)
            Log.d("LoginScreenViewModel", "authenticateUser result: $user")
            onResult(user)
        }
    }

    fun getUserByNickName(nickName: String, onResult: (MockUsers?) -> Unit) {
        Log.d("LoginScreenViewModel", "getUserByNickName called with nickName: $nickName")
        viewModelScope.launch {
            val user = usersRepository.getUserByNickName(nickName)
            Log.d("LoginScreenViewModel", "getUserByNickName result: $user")
            onResult(user)
        }
    }
}