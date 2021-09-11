package com.example.mysmarthome.di

import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.repository.DeviceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDeviceRepository(apiService: ApiService) = DeviceRepository(apiService)
}