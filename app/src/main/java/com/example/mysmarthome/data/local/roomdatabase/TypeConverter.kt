package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.mysmarthome.model.*
import com.squareup.moshi.Moshi

@ProvidedTypeConverter
class TypeConverter(private val moshi: Moshi) {

    @TypeConverter
    fun addressToJson(address: User.Address?): String = moshi.adapter(User.Address::class.java).toJson(address)

    @TypeConverter
    fun jsonToAddress(json: String) =  moshi.adapter(User.Address::class.java).fromJson(json)

    @TypeConverter
    fun deviceModeToJson(deviceMode: DeviceMode?): String = moshi.adapter(DeviceMode::class.java).toJson(deviceMode)

    @TypeConverter
    fun jsonToDeviceMode(json: String): DeviceMode? = moshi.adapter(DeviceMode::class.java).fromJson(json)

    @TypeConverter
    fun productTypeToJson(productType: ProductType?): String = moshi.adapter(ProductType::class.java).toJson(productType)

    @TypeConverter
    fun jsonToProductType(json: String) = moshi.adapter(ProductType::class.java).fromJson(json)
}
