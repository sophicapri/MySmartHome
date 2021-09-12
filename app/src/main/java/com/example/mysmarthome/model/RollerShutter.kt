package com.example.mysmarthome.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.RollerShutterEntity

data class RollerShutter(
    val id: Int,
    val deviceName: String,
    var position: Int
) : Device(ProductType.ROLLER_SHUTTER) {

    fun toRollerShutterEntity() = RollerShutterEntity(id, deviceName, position, productType)

    fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName, productType = productType,
        position = position, temperature = null, mode = null, intensity = null)
}