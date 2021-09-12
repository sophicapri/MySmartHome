package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.TypeConverter
import com.example.mysmarthome.model.*
import com.google.gson.Gson

class TypeConverter {

    @TypeConverter
    fun addressToJson(address: Address?): String = Gson().toJson(address)

    @TypeConverter
    fun jsonToAddress(json: String): Address = Gson().fromJson(json, Address::class.java)

    @TypeConverter
    fun deviceModeToJson(deviceMode: DeviceMode?): String = Gson().toJson(deviceMode)

    @TypeConverter
    fun jsonToDeviceMode(json: String): DeviceMode? = Gson().fromJson(json, DeviceMode::class.java)

    @TypeConverter
    fun productTypeToJson(productType: ProductType?): String = Gson().toJson(productType)

    @TypeConverter
    fun jsonToProductType(json: String): ProductType =
        Gson().fromJson(json, ProductType::class.java)
}
