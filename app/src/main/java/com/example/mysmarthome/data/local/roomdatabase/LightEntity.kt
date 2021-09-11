package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.ProductType

@Entity
data class LightEntity(
    @PrimaryKey val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var intensity: Int,
    var productType: ProductType
) {
    fun toLight() : Light = Light(id, deviceName, mode, intensity)
}