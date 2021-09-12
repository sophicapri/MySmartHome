package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.model.*

@Entity
class DeviceEntity(
    @PrimaryKey val id: Int,
    val deviceName: String,
    var mode: DeviceMode?,
    var intensity: Int?,
    var temperature: Int?,
    var position: Int?,
    var productType: ProductType
) {

    fun toDomainObj(): Device {
        val device = when (this.productType) {
            ProductType.LIGHT -> toLight()
            ProductType.HEATER -> toHeater()
            ProductType.ROLLER_SHUTTER -> toRollerShutter()
        }
        return device
    }

    private fun toLight(): Light = Light(id, deviceName, mode!!, intensity!!)

    private fun toHeater() : Heater = Heater(id, deviceName, mode!!, temperature!!)

    private fun toRollerShutter() = RollerShutter(id, deviceName, position!!)

}