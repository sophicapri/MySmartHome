package com.example.mysmarthome.model

import android.os.Parcelable
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker
import kotlinx.parcelize.Parcelize

@Parcelize
data class RollerShutter(
    override val id: Int,
    val deviceName: String,
    var position: Int
) : Device(id, ProductType.ROLLER_SHUTTER), EntityMaker, Parcelable {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName, productType = productType,
        position = position, temperature = null, mode = null, intensity = null)
}