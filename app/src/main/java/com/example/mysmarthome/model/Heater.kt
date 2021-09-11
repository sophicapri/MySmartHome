package com.example.mysmarthome.model

data class Heater(
    val id: Int,
    val deviceName: String,
    var mode: String?,
    var intensity: Int?
) : Device(ProductType.HEATER)