package com.example.mysmarthome.data.local.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mysmarthome.model.*

@Database(
    entities = [User::class, DeviceEntity::class], version = 2, exportSchema = false
)
@TypeConverters(TypeConverter::class)
abstract class MySmartHomeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun deviceDao(): DeviceDao

    companion object {
        const val DATABASE_NAME = "MySmartHomeDatabase.db"
    }
}
