package com.example.savr.data.repository

import com.example.savr.data.database.dao.UserDao
import com.example.savr.data.database.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    // Function to insert a user
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    // Function to retrieve a user by email and password
    fun getUserByEmailAndPassword(email: String, password: String): Flow<User?> {
        return userDao.getUserByEmailAndPassword(email, password)
    }

    // Function to retrieve a user by their unique ID
    fun getUserById(userId: Int): Flow<User?> {
        return userDao.getUserById(userId)
    }
}