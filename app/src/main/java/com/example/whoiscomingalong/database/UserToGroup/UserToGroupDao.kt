package com.example.whoiscomingalong.database.UserToGroup

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserToGroupDao {
    @Query("SELECT * FROM UserToGroup")
    fun getAll(): Flow<List<UserToGroup>>

    /*
    @Query("SELECT * FROM UserToGroup WHERE groupId = :groupId")
    fun getAll(): Flow<List<UserToGroup>> */

    @Insert
    suspend fun insertAll(vararg userToGroups: UserToGroup)

    @Delete
    suspend fun delete(userToGroup: UserToGroup)
}