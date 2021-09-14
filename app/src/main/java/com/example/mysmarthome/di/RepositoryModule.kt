package com.example.mysmarthome.di

import android.content.Context
import com.example.mysmarthome.data.local.datastore.UserPreferences
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.UserDao
import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.helper.SchedulerProvider
import com.example.mysmarthome.repository.DeviceRepository
import com.example.mysmarthome.repository.RemoteDataRepository
import com.example.mysmarthome.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDeviceRepository(deviceDao: DeviceDao) = DeviceRepository(deviceDao)

    @Singleton
    @Provides
    fun provideRemoteDataRepository(apiService: ApiService) = RemoteDataRepository(apiService)

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao) = UserRepository(userDao)


    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context) = UserPreferences(context)

    @Provides
    fun provideScheduler() = SchedulerProvider()
}