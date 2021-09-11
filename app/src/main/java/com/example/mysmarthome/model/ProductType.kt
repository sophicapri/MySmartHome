package com.example.mysmarthome.model

import com.squareup.moshi.Json

enum class ProductType(val value: String) {
    @Json(name = "RollerShutter")ROLLER_SHUTTER("RollerShutter"),
    @Json(name = "Light")LIGHT("Light"),
    @Json(name = "Heater")HEATER("Heater")
}