package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker

data class RollerShutter(
    override val id: Int,
    val deviceName: String,
    var position: Int
) : Device(id, ProductType.ROLLER_SHUTTER), EntityMaker {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName, productType = productType,
        position = position, temperature = null, mode = null, intensity = null)
}