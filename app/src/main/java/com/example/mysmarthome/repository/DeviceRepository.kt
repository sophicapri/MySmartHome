package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import com.example.mysmarthome.data.local.roomdatabase.*
import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.model.*
import io.reactivex.rxjava3.core.Flowable

class DeviceRepository(private val apiService: ApiService, val deviceDao: DeviceDao) : IDeviceRepository {

    override fun getDataFromRemote(): Flowable<ApiResponse> = apiService.queryData()

    suspend fun insertLights(lights: List<Light>){
        deviceDao.insertLights(lights.map { it.toDeviceEntity() })
    }

    suspend fun insertHeaters(heater: List<Heater>){
        deviceDao.insertHeaters(heater.map { it.toDeviceEntity() })
    }

    suspend fun insertRollerShutters(rollerShutter: List<RollerShutter>){
        deviceDao.insertRollerShutters(rollerShutter.map { it.toDeviceEntity() })
    }

    fun getDeviceListFromLocal(): LiveData<List<DeviceEntity>> {
        return deviceDao.getDeviceList()
    }

    fun getFilteredList(): LiveData<List<DeviceEntity>> {
        return deviceDao.getFilteredList()
    }
}