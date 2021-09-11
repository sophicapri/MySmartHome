package com.example.mysmarthome.model

data class Light(
    val id: Int,
    val deviceName: String,
    var mode: String?,
    var intensity: Int?
) : Device(ProductType.LIGHT)