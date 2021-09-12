package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker

data class RollerShutter(
    val id: Int,
    val deviceName: String,
    var position: Int
) : Device(ProductType.ROLLER_SHUTTER), EntityMaker {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName, productType = productType,
        position = position, temperature = null, mode = null, intensity = null)
}