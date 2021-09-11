package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import com.example.mysmarthome.data.local.roomdatabase.DeviceDao
import com.example.mysmarthome.data.local.roomdatabase.HeaterEntity
import com.example.mysmarthome.data.local.roomdatabase.LightEntity
import com.example.mysmarthome.data.local.roomdatabase.RollerShutterEntity
import com.example.mysmarthome.data.remote.ApiService
import com.example.mysmarthome.model.*
import io.reactivex.rxjava3.core.Flowable

class DeviceRepository(val apiService: ApiService, val deviceDao: DeviceDao) : IDeviceRepository {

    override fun getDataFromRemote(): Flowable<ApiResponse> = apiService.queryData()

    suspend fun insertLights(lights: List<Light>){
        deviceDao.insertLights(lights.map { it.toLightEntity() })
    }

    suspend fun insertHeaters(heater: List<Heater>){
        deviceDao.insertHeaters(heater.map { it.toHeaterEntity() })
    }

    suspend fun insertRollerShutters(rollerShutter: List<RollerShutter>){
        deviceDao.insertRollerShutters(rollerShutter.map { it.toRollerShutterEntity() })
    }

    fun getHeaterListFromLocal(): LiveData<List<HeaterEntity>> {
        return deviceDao.getHeaterList()
    }

    fun getLightListFromLocal(): LiveData<List<LightEntity>> {
        return deviceDao.getLightList()
    }

    fun getRollerShutterListFromLocal(): LiveData<List<RollerShutterEntity>> {
        return deviceDao.getRollerShutterList()
    }
}