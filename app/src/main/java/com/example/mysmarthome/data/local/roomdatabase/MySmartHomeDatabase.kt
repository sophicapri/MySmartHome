package com.example.mysmarthome.data.local.roomdatabase

import android.bluetooth.BluetoothClass
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mysmarthome.model.*

@Database(
    entities = [User::class, LightEntity::class, HeaterEntity::class, RollerShutterEntity::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class MySmartHomeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun deviceDao(): DeviceDao

    companion object {
        @Volatile
        private var instance: MySmartHomeDatabase? = null
        const val DATABASE_NAME = "MySmartHomeDatabase.db"

        fun getInstance(context: Context): MySmartHomeDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MySmartHomeDatabase =
            Room.databaseBuilder(
                context.applicationContext, MySmartHomeDatabase::class.java, DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
