package com.example.mysmarthome.repository

import androidx.lifecycle.LiveData
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.RollerShutter

interface IDeviceRepository {
    suspend fun insertLights(lights: List<Light>)

    suspend fun insertHeaters(heater: List<Heater>)

    suspend fun insertRollerShutters(rollerShutter: List<RollerShutter>)

    fun getDeviceListFromLocal(): LiveData<List<Device>>

    fun getFilteredList(query: SupportSQLiteQuery): LiveData<List<Device>>

    fun getDeviceById(id: Int): LiveData<Device>

    suspend fun updateDevice(device: Device)
}
