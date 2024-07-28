package com.example.whoiscomingalong.database.Group

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM `Group`")
    fun getAll(): Flow<List<Group>>

    @Query("SELECT * FROM `Group` WHERE groupId = :groupId")
    fun getById(groupId: Int): Flow<Group>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg groups: Group)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(group: Group)

    @Update
    suspend fun update(group: Group)

    @Delete
    suspend fun delete(group: Group)
}