package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import com.example.mysmarthome.model.User

interface IUserRepository {
    suspend fun insertUser(user: User)

    fun getUser(): LiveData<User>

    suspend fun updateUser(user: User)
}
