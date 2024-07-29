package com.example.whoiscomingalong.database.Users

import androidx.room.*
import kotlinx.coroutines.flow.Flow

// DAOs or data access obejcts define which actions can be applied on
// entities (i.e. tables) of the database, one is created for each of
// the entities
// Room provides abstracted functionality for database usage, examples
// are: @Query("... SQL command ..."), @Insert, @Update, @Delete

@Dao
interface UsersDao {

    // info: queries are *not* called from suspend functions, as they already
    // return an asynchronous Flow

    @Query("SELECT * FROM Users WHERE userId = :userId")
    fun getUserById(userId: Int): Flow<Users>

    @Query("SELECT * FROM Users")
    fun getAllUsers(): Flow<List<Users>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Users)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: Users)

    @Delete
    suspend fun delete(user: Users)

    @Update
    suspend fun update(user: Users)

    @Query("SELECT * FROM Users WHERE nickName = :nickName AND password = :password")
    suspend fun authenticateUser(nickName: String, password: String): Users?

    @Query("SELECT * FROM Users WHERE nickName = :nickName")
    suspend fun getUserByNickName(nickName: String): Users?
}