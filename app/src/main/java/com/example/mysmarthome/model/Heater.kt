package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker


data class Heater(
    override val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Int
) : Device(id, ProductType.HEATER), EntityMaker {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, temperature = temperature,
        productType = productType, intensity = null, position = null
    )
}


