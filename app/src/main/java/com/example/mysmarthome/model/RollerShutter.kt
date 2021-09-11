package com.example.mysmarthome.model

data class RollerShutter(val id: Int, val deviceName: String, var position: Int?) :
    Device(ProductType.ROLLER_SHUTTER)