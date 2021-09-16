package com.example.mysmarthome

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.ProductType

class DummyData {
    companion object{
        val lightEntity = DeviceEntity(
            1, "", DeviceMode.OFF,
            0, null, null, ProductType.LIGHT
        )
        val rollerShutterEntity = DeviceEntity(
            2, "", DeviceMode.ON,
            0, null, 0, ProductType.ROLLER_SHUTTER
        )

        val light = lightEntity.toDomainObj()
        val rollerShutter = rollerShutterEntity.toDomainObj()
    }
}