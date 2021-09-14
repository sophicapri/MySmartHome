package com.example.mysmarthome.model

import android.os.Parcelable
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker
import kotlinx.parcelize.Parcelize

@Parcelize
data class Heater(
    override val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Float
) : Device(id, ProductType.HEATER), EntityMaker, Parcelable {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, temperature = temperature,
        productType = productType, intensity = null, position = null
    )
}


