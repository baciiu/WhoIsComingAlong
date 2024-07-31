package com.example.whoiscomingalong.ui.theme.screens.groupscreen

import android.app.Application
import android.util.Log
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
) : AndroidViewModel(application) {

    private val _allUsers = MutableStateFlow<List<Users>>(emptyList())
    val allUsers: StateFlow<List<Users>> = _allUsers

    private val _allGroups = MutableStateFlow<List<Group>>(emptyList())
    val allGroups: StateFlow<List<Group>> = _allGroups

    private val TAG = "GroupScreenValidation"


    init {
        loadAllUsers()
        loadAllGroups()
    }

    private fun loadAllUsers() {
        viewModelScope.launch {
            usersRepository.getAllUsers().collect { users ->
                _allUsers.value = users
            }
        }
    }

    private fun loadAllGroups() {
        viewModelScope.launch {
            groupRepository.getAllGroups().collect { groups ->
                _allGroups.value = groups
            }
        }
    }

    fun getAllGroups(): Flow<List<Group>> {
        return groupRepository.getAllGroups()
    }

    fun getAllUsers(): Flow<List<Users>> {
        return usersRepository.getAllUsers()
    }

    fun getGroupById(groupId: Int): Flow<Group> {
        return groupRepository.getGroupById(groupId)
    }

    suspend fun insertGroup(group: Group):Int {
        groupRepository.insert(group)
        Log.d(TAG, "Group inserted successfully with ID: ${group.groupId}")
        return group.groupId
    }

    fun updateGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.update(group)
            Log.d(TAG, "Group updated successfully with ID: ${group.groupId}")
        }
    }

    fun deleteGroup(group: Group) {
        viewModelScope.launch(Dispatchers.IO) {
            groupRepository.delete(group)
            Log.d(TAG, "Group deleted successfully with ID: ${group.groupId}")
        }
    }

    fun addUserToGroup(userId: Int, groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (userToGroupRepository.countUserToGroup(userId, groupId) == 0) {
                val userToGroup = UserToGroup(userId = userId, groupId = groupId)
                userToGroupRepository.insert(userToGroup)
                Log.d(TAG, "User added to group successfully with userID: $userId and groupID: $groupId")
            }
            else {
                Log.w(TAG, "User already exists in group with userID: $userId and groupID: $groupId")
            }
        }
    }


    fun removeUserFromGroup(userToGroup: UserToGroup) {
        viewModelScope.launch(Dispatchers.IO) {
            userToGroupRepository.delete(userToGroup)
            Log.d(TAG, "User removed from group successfully with userID: ${userToGroup.userId} and groupID: ${userToGroup.groupId}")
        }
    }

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

    private fun validateGroup(group: Group): Group {
        var validGroup = group

        // Check if group name is not empty
        if (group.groupName.isBlank()) {
            Log.e(TAG, "Group name is blank, setting default value")
            validGroup = validGroup.copy(groupName = "Default Group Name")
        } else {
            Log.d(TAG, "Group name is valid")
        }

        return validGroup
    }

    private fun validateUserToGroup(userToGroup: UserToGroup): UserToGroup {
        var validUserToGroup = userToGroup

        // Check if userId is a positive integer
        if (userToGroup.userId <= 0) {
            Log.e(TAG, "User ID is not valid: ${userToGroup.userId}, setting default value")
            validUserToGroup = validUserToGroup.copy(userId = 1)
        } else {
            Log.d(TAG, "User ID is valid: ${userToGroup.userId}")
        }

        // Check if groupId is a positive integer
        if (userToGroup.groupId <= 0) {
            Log.e(TAG, "Group ID is not valid: ${userToGroup.groupId}, setting default value")
            validUserToGroup = validUserToGroup.copy(groupId = 1)
        } else {
            Log.d(TAG, "Group ID is valid: ${userToGroup.groupId}")
        }

        return validUserToGroup
    }
}
