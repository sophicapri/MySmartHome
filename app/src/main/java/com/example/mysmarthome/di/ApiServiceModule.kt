package com.example.mysmarthome.di

import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.model.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    fun provideApiService(): ApiService {
        val jsonAdapterFactory =
            PolymorphicJsonAdapterFactory.of(Device::class.java, Device.LABEL_KEY)
                .withSubtype(Light::class.java, ProductType.LIGHT.value)
                .withSubtype(Heater::class.java, ProductType.HEATER.value)
                .withSubtype(RollerShutter::class.java, ProductType.ROLLER_SHUTTER.value)

        val moshi = Moshi.Builder()
            .add(jsonAdapterFactory)
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder().baseUrl(ApiService.API_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
            .create(ApiService::class.java)
    }
}