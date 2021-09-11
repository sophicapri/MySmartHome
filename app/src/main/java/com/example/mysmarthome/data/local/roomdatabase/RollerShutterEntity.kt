package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mysmarthome.model.ProductType
import com.example.mysmarthome.model.RollerShutter

@Entity
data class RollerShutterEntity(
    @PrimaryKey val id: Int,
    val deviceName: String,
    var position: Int,
    val productType: ProductType
) {
    fun toRollerShutter() = RollerShutter(id, deviceName, position)
}