package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
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
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val newUser = Users(
                    firstName = firstName,
                    lastName = lastName,
                    dateOfBirth = dateOfBirth,
                    company = company,
                    department = department,
                    email = email,
                    nickName = nickName,
                    password = password
                )
                usersRepository.insert(newUser)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun getUserByNickName(nickName: String, onResult: (Users?) -> Unit) {
        viewModelScope.launch {
            val user = usersRepository.getUserByNickName(nickName)
            onResult(user)
        }
    }
}