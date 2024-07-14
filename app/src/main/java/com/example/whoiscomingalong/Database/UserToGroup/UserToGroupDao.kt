package com.example.whoiscomingalong.Database.UserToGroup

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserToGroupDao {
    @Query("SELECT * FROM UserToGroup")
    fun getAll(): Flow<List<UserToGroup>>

    @Insert
    fun insertAll(vararg userToGroups: UserToGroup)

    @Delete
    fun delete(userToGroup: UserToGroup)
}