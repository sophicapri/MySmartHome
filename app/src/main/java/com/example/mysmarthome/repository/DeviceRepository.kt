package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysmarthome.data.local.roomdatabase.*
import com.example.mysmarthome.model.*

class DeviceRepository(val deviceDao: DeviceDao) {

    suspend fun insertDeviceList(devices: List<Device>) {
        deviceDao.insertDeviceList(devices.map { it.toDeviceEntity() })
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
        deviceDao.updateDevice(device.toDeviceEntity())
    }

    suspend fun deleteDeviceList(devices: List<Device>) {
        val deviceEntityList = devices.map { device -> device.toDeviceEntity() }
        deviceDao.deleteDeviceList(deviceEntityList)
    }

    suspend fun insertDevice(device: Device): Long {
        return deviceDao.insertDevice(device.toDeviceEntity())
    }
}