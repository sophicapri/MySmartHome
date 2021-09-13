package com.example.mysmarthome.model

import android.os.Parcelable
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker
import kotlinx.parcelize.Parcelize

@Parcelize
data class Light(
    override val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var intensity: Int
) : Device(id, ProductType.LIGHT), EntityMaker, Parcelable{

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, productType = productType,
        intensity = intensity, position = null, temperature = null
    )
}
