package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import com.example.mysmarthome.data.local.roomdatabase.UserDao
import com.example.mysmarthome.model.User

class UserRepository(private var userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    fun getUser(): LiveData<User> {
       return userDao.getUser()
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}