package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysmarthome.data.local.roomdatabase.*
import com.example.mysmarthome.model.*

class DeviceRepository(val deviceDao: DeviceDao) {

    suspend fun insertLights(lights: List<Light>) {
        deviceDao.insertLights(lights.map { it.toDeviceEntity() })
    }

    suspend fun insertHeaters(heater: List<Heater>) {
        deviceDao.insertHeaters(heater.map { it.toDeviceEntity() })
    }

    suspend fun insertRollerShutters(rollerShutter: List<RollerShutter>) {
        deviceDao.insertRollerShutters(rollerShutter.map { it.toDeviceEntity() })
    }

    fun getDeviceList(): LiveData<List<Device>> {
        return Transformations.map(deviceDao.getDeviceList()) { list ->
            list.map { it.toDomainObj() }
        }
    }

    fun getFilteredList(query: SupportSQLiteQuery): LiveData<List<Device>> {
        return Transformations.map(deviceDao.getFilteredList(query)) { list ->
            list.map { it.toDomainObj() }
        }
    }

    suspend fun updateDevice(device: Device) {
        when (device) {
            is Light -> deviceDao.updateDevice(device.toDeviceEntity())
            is Heater -> deviceDao.updateDevice(device.toDeviceEntity())
            is RollerShutter -> deviceDao.updateDevice(device.toDeviceEntity())
        }
    }

    suspend fun deleteDevices(devices: List<Device>) {
        val deviceEntityList = devices.map { device ->
            when (device) {
                is Light -> device.toDeviceEntity()
                is Heater -> device.toDeviceEntity()
                else -> (device as RollerShutter).toDeviceEntity()
            }
        }
        deviceDao.deleteDevices(deviceEntityList)
    }

    suspend fun insertDevice(device: Device): Long {
        return when (device){
            is Light -> deviceDao.insertDevice(device.toDeviceEntity())
            is Heater -> deviceDao.insertDevice(device.toDeviceEntity())
            is RollerShutter -> deviceDao.insertDevice(device.toDeviceEntity())
        }
    }

    /*  fun getDeviceById(id: Int): LiveData<Device> {
      return Transformations.map(deviceDao.getDeviceById(id)) { device ->
          device.toDomainObj()
      }
  }*/
}