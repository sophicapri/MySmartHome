package com.example.mysmarthome.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.data.local.roomdatabase.LightEntity

data class Light(
    val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var intensity: Int
) : Device(ProductType.LIGHT){

    fun toLightEntity() = LightEntity(id, deviceName, mode, intensity, productType)
}
