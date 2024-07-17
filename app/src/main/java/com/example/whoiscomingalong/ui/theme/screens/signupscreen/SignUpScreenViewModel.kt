package com.example.whoiscomingalong.ui.theme.screens.signupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    fun insertUser(user: Users) {
        viewModelScope.launch (Dispatchers.IO){
            usersRepository.insert(user)
        }
    }
}

/*
@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {

    // remarks: .stateIn transforms the Flow created in the dao (UsersDao) into a StateFlow
    // thereby allowing the ViewModel to use the data from the dao,
    // default is an empty list

    val allUsers: StateFlow<List<Users>> = usersRepository.allUsers
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertUser(user: Users) {
        viewModelScope.launch (Dispatchers.IO) {
            usersRepository.insert(user)
            // db.usersDao().insertAll(user)

        }
    }
}
 */