package com.example.mysmarthome.model

import androidx.room.Entity
import androidx.room.PrimaryKey

open class Device(var productType: ProductType){
    companion object {
        const val LABEL_KEY = "productType"
    }
}