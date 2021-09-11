package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.HeaterEntity


data class Heater(
    val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Int
) : Device(ProductType.HEATER){

    fun toHeaterEntity() = HeaterEntity(id, deviceName, mode, temperature, productType)

}


