package com.example.mysmarthome.di

import android.content.Context
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.MySmartHomeDatabase
import com.example.mysmarthome.data.local.roomdatabase.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MySmartHomeDatabase {
        return MySmartHomeDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providePropertyDao(database: MySmartHomeDatabase): DeviceDao {
        return database.deviceDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: MySmartHomeDatabase): UserDao {
        return database.userDao()
    }

}
