package com.example.mysmarthome.model

data class Heater(
    val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Int
) : Device(ProductType.HEATER)