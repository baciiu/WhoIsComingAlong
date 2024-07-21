package com.example.whoiscomingalong.ui.theme.screens.groupscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.whoiscomingalong.database.Group.Group
import com.example.whoiscomingalong.database.Group.GroupRepository
import com.example.whoiscomingalong.database.UserToGroup.UserToGroup
import com.example.whoiscomingalong.database.UserToGroup.UserToGroupRepository
import com.example.whoiscomingalong.database.Users.Users
import com.example.whoiscomingalong.database.Users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupScreenViewModel @Inject constructor(
    application: Application,
    private val usersRepository: UsersRepository,
    private val groupRepository: GroupRepository,
    private val userToGroupRepository: UserToGroupRepository
) : AndroidViewModel(application){

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getAllUsers(): Flow<List<Users>> {
        return usersRepository.getAllUsers()
    }

    fun insertGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.insert(group)
        }
    }

    fun deleteGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.delete(group)
        }
    }

    fun addUserToGroup(userId: Int, groupId: Int) {
        val userToGroup = UserToGroup(userId = userId, groupId = groupId)
        viewModelScope.launch(Dispatchers.IO) {
            userToGroupRepository.insert(userToGroup)
        }
    }

    fun removeUserFromGroup(userToGroup: UserToGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            userToGroupRepository.delete(userToGroup)
        }
    }

    // todo get all users of a specific group (or implement code in Screen-class)
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getUsersOfGroup(groupId: Int): Flow<List<Users>> {
        return userToGroupRepository.getAllUsersToGroup()
            .map { userToGroups ->
                val userIds = userToGroups.filter { it.groupId == groupId }.map { it.userId }
                usersRepository.getAllUsers().map { users ->
                    users.filter { it.userId in userIds }
                }
            }
            .flatMapLatest { it }
    }
}