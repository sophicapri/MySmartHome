package com.example.mysmarthome.data.local.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mysmarthome.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User): Int

    //In a real world situation it would be better to query the user by its id.
    @Query("SELECT * FROM user")
    fun getUser(): LiveData<User>

}