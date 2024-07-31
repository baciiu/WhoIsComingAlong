package com.example.whoiscomingalong.ui.theme.screens.profilescreen

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    application: Application,
    private val userRepository: UsersRepository
) : AndroidViewModel(application) {

    private val TAG = "ProfileScreenValidation"


    fun getUserById(userId: Int): Flow<Users?> {
        return userRepository.getUserById(userId)
    }

    fun updateUser(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user)
            Log.d(TAG, "User updated successfully: $user")
        }
    }

    private fun validateUser(user: Users): Boolean {
        var isValid = true

        if (user.firstName.isBlank()) {
            Log.e(TAG, "First name is blank")
            isValid = false
        } else {
            Log.d(TAG, "First name is valid")
        }

        if (user.lastName.isBlank()) {
            Log.e(TAG, "Last name is blank")
            isValid = false
        } else {
            Log.d(TAG, "Last name is valid")
        }

        if (user.nickName.isBlank()) {
            Log.e(TAG, "Nick name is blank")
            isValid = false
        } else {
            Log.d(TAG, "Nick name is valid")
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()) {
            Log.e(TAG, "Invalid email address")
            isValid = false
        } else {
            Log.d(TAG, "Email address is valid")
        }

        if (user.dateOfBirth.after(java.util.Date())) {
            Log.e(TAG, "Date of birth is in the future")
            isValid = false
        } else {
            Log.d(TAG, "Date of birth is valid")
        }

        return isValid
    }
}