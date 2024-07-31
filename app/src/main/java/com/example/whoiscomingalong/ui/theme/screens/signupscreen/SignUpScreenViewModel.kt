package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.Network.HelperData.SignUpRequest
import com.example.whoiscomingalong.database.Users.UsersRepository
import com.example.whoiscomingalong.Network.HelperData.SignUpResponse
import com.example.whoiscomingalong.database.Users.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    application: Application,
    private val userRepository: UsersRepository
) : AndroidViewModel(application) {

    fun signUp(
        firstName: String,
        lastName: String,
        dateOfBirth: Date,
        company: String,
        department: String,
        email: String,
        nickName: String,
        password: String,
        onResult: (SignUpResponse?) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = userRepository.signUp(
                    SignUpRequest(firstName, lastName, dateOfBirth, company, department, email, nickName, password)
                )
                onResult(response)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun getUserByNickName(nickName: String, onResult: (Users?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByNickName(nickName)
            onResult(user)
        }
    }
}