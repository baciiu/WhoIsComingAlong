package com.example.whoiscomingalong.database.Users

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

// repositories are classes used by Hilt to create functions that
// can be used by the view models
// they are created only once during the application, the DAOs of the
// corresponding DAO classes are injected during initialization

@Singleton
class UsersRepository @Inject constructor(private val usersDao: UsersDao) {

    fun getUserById(userId: Int): Flow<Users> {
        return usersDao.getUserById(userId)
    }

    fun getAllUsers(): Flow<List<Users>> {
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

    suspend fun authenticateUser(nickName: String, password: String): Users? {
        return usersDao.authenticateUser(nickName, password)
    }

    suspend fun getUserByNickName(nickName: String): Users? {
        return usersDao.getUserByNickName(nickName)
    }
}