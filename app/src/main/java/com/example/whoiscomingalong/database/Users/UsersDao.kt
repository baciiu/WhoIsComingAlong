package com.example.whoiscomingalong.database.Users

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * FROM Users WHERE userId = :userId")
    fun getUserById(userId: Int): Flow<Users>

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<Users>>

    @Insert
    fun insert(user: Users)

    @Insert // todo: delete insertAll if not needed
    fun insertAll(vararg users: Users)

    @Delete
    fun delete(user: Users)

    @Update
    fun update(user: Users)
}