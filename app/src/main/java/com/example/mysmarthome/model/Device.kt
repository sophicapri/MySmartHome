package com.example.mysmarthome.model

import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.databinding.DeviceItemBinding

sealed class Device(open val id: Int, var productType: ProductType) {
    companion object {
        const val LABEL_KEY = "productType"
    }

    abstract fun bindDevice(binding: DeviceItemBinding)

    abstract fun toDeviceEntity(): DeviceEntity
}