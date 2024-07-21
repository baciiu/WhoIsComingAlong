package com.example.whoiscomingalong.database.UserToGroup

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserToGroupRepository @Inject constructor(private val userToGroupDao: UserToGroupDao) {

    fun getAllUsersToGroup(): Flow<List<UserToGroup>> {
        return userToGroupDao.getAll()
    }

    fun getAllUsersToGroupById(groupId: Int): Flow<List<UserToGroup>> {
        return userToGroupDao.getAllUsersToGroupById(groupId)
    }

    suspend fun insert(userToGroup: UserToGroup) {
        userToGroupDao.insertAll(userToGroup)
    }

    suspend fun insertAll(vararg userToGroups: UserToGroup) {
        userToGroupDao.insertAll(*userToGroups)
    }

    suspend fun delete(userToGroup: UserToGroup) {
        userToGroupDao.delete(userToGroup)
    }
}