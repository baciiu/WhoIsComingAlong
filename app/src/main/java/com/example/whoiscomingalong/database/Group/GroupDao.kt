package com.example.whoiscomingalong.database.Group

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM `Group`")
    fun getAll(): Flow<List<Group>>

    @Query("SELECT * FROM `Group` WHERE groupId = :groupId")
    fun getById(groupId: Int): Flow<Group>

    @Insert
    suspend fun insertAll(vararg groups: Group)

    @Insert
    suspend fun insert(group: Group): Long

    @Delete
    suspend fun delete(group: Group)
}