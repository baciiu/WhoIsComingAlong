package com.example.whoiscomingalong.Database.Group

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GroupDao {
    @Query("SELECT * FROM `Group`")
    fun getAll(): Flow<List<Group>>

    @Insert
    fun insertAll(vararg groups: Group)

    @Delete
    fun delete(group: Group)
}