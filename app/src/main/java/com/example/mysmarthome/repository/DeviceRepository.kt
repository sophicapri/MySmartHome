package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysmarthome.data.local.roomdatabase.*
import com.example.mysmarthome.model.*

class DeviceRepository(val deviceDao: DeviceDao) {

    suspend fun insertLightList(lights: List<Light>) {
        deviceDao.insertLightList(lights.map { it.toDeviceEntity() })
    }

    suspend fun insertHeaterList(heater: List<Heater>) {
        deviceDao.insertHeaterList(heater.map { it.toDeviceEntity() })
    }

    suspend fun insertRollerShutterList(rollerShutter: List<RollerShutter>) {
        deviceDao.insertRollerShutterList(rollerShutter.map { it.toDeviceEntity() })
    }

    fun getDeviceList(): LiveData<List<Device>> {
        return Transformations.map(deviceDao.getDeviceList()) { list ->
            list.map { it.toDomainObj() }
        }
    }

    fun getFilteredDeviceList(query: SupportSQLiteQuery): LiveData<List<Device>> {
        return Transformations.map(deviceDao.getFilteredDeviceList(query)) { list ->
            list.map { it.toDomainObj() }
        }
    }

    suspend fun updateDevice(device: Device)  {
        when (device) {
            is Light -> deviceDao.updateDevice(device.toDeviceEntity())
            is Heater -> deviceDao.updateDevice(device.toDeviceEntity())
            is RollerShutter -> deviceDao.updateDevice(device.toDeviceEntity())
        }
    }

    suspend fun deleteDeviceList(devices: List<Device>) {
        val deviceEntityList = devices.map { device ->
            when (device) {
                is Light -> device.toDeviceEntity()
                is Heater -> device.toDeviceEntity()
                else -> (device as RollerShutter).toDeviceEntity()
            }
        }
        deviceDao.deleteDeviceList(deviceEntityList)
    }

    suspend fun insertDevice(device: Device): Long {
        return when (device){
            is Light -> deviceDao.insertDevice(device.toDeviceEntity())
            is Heater -> deviceDao.insertDevice(device.toDeviceEntity())
            is RollerShutter -> deviceDao.insertDevice(device.toDeviceEntity())
        }
    }
}