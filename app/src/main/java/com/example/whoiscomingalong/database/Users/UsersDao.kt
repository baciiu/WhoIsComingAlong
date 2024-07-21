package com.example.whoiscomingalong.database.Users

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    // info: queries are *not* called from suspend functions, as they already
    // return an asynchronous Flow

    @Query("SELECT * FROM Users WHERE userId = :userId")
    fun getUserById(userId: Int): Flow<Users>

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<Users>>

    @Insert
    suspend fun insert(user: Users)

    @Insert
    suspend fun insertAll(vararg users: Users)

    @Delete
    suspend fun delete(user: Users)

    @Update
    suspend fun update(user: Users)
}