package com.example.mysmarthome.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.MySmartHomeDatabase
import com.example.mysmarthome.data.local.roomdatabase.TypeConverter
import com.example.mysmarthome.data.local.roomdatabase.UserDao
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
    fun provideDatabase(context: Application): MySmartHomeDatabase {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return Room.databaseBuilder(context, MySmartHomeDatabase::class.java, MySmartHomeDatabase.DATABASE_NAME)
            .addTypeConverter(TypeConverter(moshi))
            .fallbackToDestructiveMigration()
            .build()
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
