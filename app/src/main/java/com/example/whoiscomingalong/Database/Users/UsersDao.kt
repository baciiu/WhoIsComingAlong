package com.example.whoiscomingalong.Database.Users

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {
    @Query("SELECT * FROM Users")
    fun getAll(): Flow<List<Users>>

    @Insert
    fun insertAll(vararg users: Users)

    @Delete
    fun delete(user: Users)
}