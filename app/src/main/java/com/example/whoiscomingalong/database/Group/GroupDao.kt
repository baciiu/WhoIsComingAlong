package com.example.whoiscomingalong.database.Group

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM `Group`")
    fun getAll(): Flow<List<Group>>

    @Insert
    suspend fun insertAll(vararg groups: Group)

    @Delete
    suspend fun delete(group: Group)
}