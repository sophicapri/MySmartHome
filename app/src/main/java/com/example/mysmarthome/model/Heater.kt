package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.HeaterEntity


data class Heater(
    val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Int
) : Device(ProductType.HEATER) {

    fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, temperature = temperature,
        productType = productType, intensity = null, position = null
    )

}


