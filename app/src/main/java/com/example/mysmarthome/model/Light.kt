package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker

data class Light(
    val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var intensity: Int
) : Device(ProductType.LIGHT), EntityMaker{

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, productType = productType,
        intensity = intensity, position = null, temperature = null
    )
}
