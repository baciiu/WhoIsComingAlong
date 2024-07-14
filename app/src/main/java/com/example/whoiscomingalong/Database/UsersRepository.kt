package com.example.whoiscomingalong

import kotlinx.coroutines.flow.Flow

class UsersRepository(private val usersDao: UsersDao) {
    val allUsers: Flow<List<Users>> = usersDao.getAll()

    suspend fun insert(user: Users) {
        usersDao.insertAll(user)
    }
}