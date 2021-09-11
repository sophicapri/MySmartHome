package com.example.mysmarthome.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(val devices: List<Device>, val user: User)