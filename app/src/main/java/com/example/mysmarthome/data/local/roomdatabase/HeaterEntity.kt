package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.model.ProductType

@Entity
data class HeaterEntity(
    @PrimaryKey val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Int,
    val productType: ProductType
) {
    fun toHeater() = Heater(id, deviceName, mode, temperature)
}