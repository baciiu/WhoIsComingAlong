package com.example.whoiscomingalong.ui.theme.screens.groupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.whoiscomingalong.database.Group.GroupRepository
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupRepository
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository,
    private val groupRepository: GroupRepository,
    private val userToGroupRepository: UserToGroupRepository
) : AndroidViewModel(application){
    // todo: continue here
}

/*
@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository
) : AndroidViewModel(application) {
    fun getAllUsers() : Flow<List<Users>> {
        return usersRepository.getAllUsers()
    }

    fun getUserById(int: Int) : Flow<Users> {
        return usersRepository.getUserById(int)
    }

    fun deleteUser(user: Users) {
        viewModelScope.launch (Dispatchers.IO) {
            usersRepository.delete(user)
        }
    }
}

 */