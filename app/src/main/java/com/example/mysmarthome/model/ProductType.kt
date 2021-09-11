package com.example.mysmarthome.model

import com.squareup.moshi.Json

enum class ProductType(val value: String) {
    ROLLER_SHUTTER("RollerShutter"),
    LIGHT("Light"),
    HEATER("Heater")
}