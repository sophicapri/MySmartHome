package com.example.mysmarthome.model

import com.example.mysmarthome.databinding.DeviceItemBinding

sealed class Device(open val id: Int, var productType: ProductType){
    companion object {
        const val LABEL_KEY = "productType"
    }

    interface DeviceBinder{
        fun bindDevice(binding : DeviceItemBinding)
    }
}