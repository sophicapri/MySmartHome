package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import com.example.mysmarthome.data.local.roomdatabase.UserDao
import com.example.mysmarthome.model.User

class UserRepository(private var userDao: UserDao): IUserRepository {

    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override fun getUser(): LiveData<User> {
       return userDao.getUser()
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}