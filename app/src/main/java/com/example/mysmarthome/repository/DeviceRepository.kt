package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysmarthome.data.local.roomdatabase.*
import com.example.mysmarthome.model.*

class DeviceRepository(val deviceDao: DeviceDao) : IDeviceRepository {

    override suspend fun insertLights(lights: List<Light>) {
        deviceDao.insertLights(lights.map { it.toDeviceEntity() })
    }

    override suspend fun insertHeaters(heater: List<Heater>) {
        deviceDao.insertHeaters(heater.map { it.toDeviceEntity() })
    }

    override suspend fun insertRollerShutters(rollerShutter: List<RollerShutter>) {
        deviceDao.insertRollerShutters(rollerShutter.map { it.toDeviceEntity() })
    }

    override fun getDeviceListFromLocal(): LiveData<List<Device>> {
        return Transformations.map(deviceDao.getDeviceList()) { list ->
            list.map { it.toDomainObj() }
        }
    }

    override fun getFilteredList(query: SupportSQLiteQuery): LiveData<List<Device>> {
        return Transformations.map(deviceDao.getFilteredList(query)) { list ->
            list.map { it.toDomainObj() }
        }
    }

    override fun getDeviceById(id: Int): LiveData<Device> {
        return Transformations.map(deviceDao.getDeviceById(id)) { device ->
            device.toDomainObj()
        }
    }

    override suspend fun updateDevice(device: Device) {
        when (device) {
            is Light -> deviceDao.updateDevice(device.toDeviceEntity())
            is Heater -> deviceDao.updateDevice(device.toDeviceEntity())
            is RollerShutter -> deviceDao.updateDevice(device.toDeviceEntity())
        }
    }

    override suspend fun deleteDevices(devices: List<Device>) {
        val deviceEntityList = devices.map { device ->
            when (device) {
                is Light -> device.toDeviceEntity()
                is Heater -> device.toDeviceEntity()
                else -> (device as RollerShutter).toDeviceEntity()
            }
        }
        deviceDao.deleteDevices(deviceEntityList)
    }
}