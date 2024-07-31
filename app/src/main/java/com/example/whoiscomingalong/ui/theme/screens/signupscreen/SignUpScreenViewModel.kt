package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.app.Application
import android.util.Log
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

    private val TAG = "SignUpValidation"


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
                Log.d(TAG, "Sign up successful: $response")
                onResult(response)
            } catch (e: Exception) {
                Log.e(TAG, "Sign up failed: ${e.message}")
                onResult(null)
            }
        }
    }

    private fun validateSignUp(
        firstName: String,
        lastName: String,
        dateOfBirth: Date?,
        company: String,
        department: String,
        email: String,
        nickName: String,
        password: String
    ): Boolean {
        var isValid = true

        if (firstName.isBlank()) {
            Log.e(TAG, "First name is blank")
            isValid = false
        } else {
            Log.d(TAG, "First name is valid")
        }

        if (lastName.isBlank()) {
            Log.e(TAG, "Last name is blank")
            isValid = false
        } else {
            Log.d(TAG, "Last name is valid")
        }

        if (dateOfBirth == null || dateOfBirth.after(Date())) {
            Log.e(TAG, "Date of birth is invalid or in the future")
            isValid = false
        } else {
            Log.d(TAG, "Date of birth is valid: $dateOfBirth")
        }

        if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.e(TAG, "Email is invalid")
            isValid = false
        } else {
            Log.d(TAG, "Email is valid")
        }

        if (nickName.isBlank()) {
            Log.e(TAG, "Nick name is blank")
            isValid = false
        } else {
            Log.d(TAG, "Nick name is valid")
        }

        if (password.isBlank()) {
            Log.e(TAG, "Password is blank")
            isValid = false
        } else {
            Log.d(TAG, "Password is valid")
        }

        return isValid
    }

    fun getUserByNickName(nickName: String, onResult: (Users?) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.getUserByNickName(nickName)
            onResult(user)
        }
    }
}