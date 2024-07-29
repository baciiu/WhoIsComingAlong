package com.example.whoiscomingalong.database.UserToGroup

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserToGroupDao {
    @Query("SELECT * FROM UserToGroup")
    fun getAll(): Flow<List<UserToGroup>>

    @Query("SELECT * FROM UserToGroup WHERE groupId = :groupId")
    fun getAllUsersToGroupById(groupId: Int): Flow<List<UserToGroup>>

    @Query("SELECT COUNT(*) FROM UserToGroup WHERE userId = :userId AND groupId = :groupId")
    suspend fun countUserToGroup(userId: Int, groupId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg userToGroups: UserToGroup)

    @Delete
    suspend fun delete(userToGroup: UserToGroup)
}