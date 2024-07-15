package com.example.whoiscomingalong.Database.Users

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(private val usersDao: UsersDao) {
    val allUsers: Flow<List<Users>> = usersDao.getAllUsers()

    suspend fun getUserById(userId: Int): Flow<Users> {
        return usersDao.getUserById(userId)
    }

    suspend fun getAllUsers(): Flow<List<Users>> {
        return usersDao.getAllUsers()
    }

    suspend fun insert(user: Users) {
        usersDao.insert(user)
    }
    suspend fun insertAll(vararg users: Users) {
        usersDao.insertAll(*users)
    }

    suspend fun delete(user: Users) {
        usersDao.delete(user)
    }

    suspend fun update(user: Users) {
        usersDao.update(user)
    }
}