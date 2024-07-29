package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.mocks.MockSignUpRequest
import com.example.whoiscomingalong.mocks.MockSignUpResponse
import com.example.whoiscomingalong.mocks.MockUserRepository
import com.example.whoiscomingalong.mocks.MockUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    application: Application,
    private val userRepository: MockUserRepository
) : AndroidViewModel(application) {

    fun signUp(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        company: String,
        department: String,
        email: String,
        nickName: String,
        password: String,
        onResult: (MockSignUpResponse?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = userRepository.signUp(
                    MockSignUpRequest(firstName, lastName, dateOfBirth, company, department, email, nickName, password)
                )
                onResult(response)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun getUserByNickName(nickName: String, onResult: (MockUsers?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByNickName(nickName)
            onResult(user)
        }
    }
}