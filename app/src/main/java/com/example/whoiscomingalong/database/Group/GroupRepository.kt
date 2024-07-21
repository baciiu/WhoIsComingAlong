package com.example.whoiscomingalong.database.Group

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupRepository @Inject constructor(private val groupDao: GroupDao) {
    fun getAllGroups(): Flow<List<Group>> {
        return groupDao.getAll()
    }

    fun getGroupById(groupId: Int): Flow<Group> {
        return groupDao.getById(groupId)
    }

    suspend fun insert(group: Group) {
        groupDao.insertAll(group)
    }

    suspend fun insertAll(vararg groups: Group) {
        groupDao.insertAll(*groups)
    }

    suspend fun delete(group: Group) {
        groupDao.delete(group)
    }
}
