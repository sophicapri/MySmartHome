package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.TypeConverter
import com.example.mysmarthome.model.*
import com.google.gson.Gson

/*
*  I use Gson and not Moshi for simplicity just for this particular case, but it's probably
*  best to stick with one tool.
*
*/

class Converters {

    @TypeConverter
    fun addressToJson(address: Address?): String = Gson().toJson(address)

    @TypeConverter
    fun jsonToAddress(json: String): Address = Gson().fromJson(json, Address::class.java)

    @TypeConverter
    fun deviceModeToJson(deviceMode: DeviceMode?): String = Gson().toJson(deviceMode)

    @TypeConverter
    fun jsonToDeviceMode(json: String): DeviceMode = Gson().fromJson(json, DeviceMode::class.java)

    @TypeConverter
    fun productTypeToJson(productType: ProductType?): String = Gson().toJson(productType)

    @TypeConverter
    fun jsonToProductType(json: String): ProductType =
        Gson().fromJson(json, ProductType::class.java)

 /*   @TypeConverter
    fun jsonToDevice(json: String): Device {
        val device: Device = when {
            json.contains(ProductType.ROLLER_SHUTTER.value) ->
                Gson().fromJson(json, RollerShutter::class.java)
            json.contains(ProductType.HEATER.value) ->
                Gson().fromJson(json, RollerShutter::class.java)
            else -> Gson().fromJson(json, Light::class.java)
        }
        return device
    }*/

/*    @TypeConverter
    fun deviceToJson(device: Device): String{
        val json = when {
            device.productType == ProductType.LIGHT -> Gson().toJson(device as Light)
            device.productType == ProductType.HEATER -> Gson().toJson(device as Heater)
            else -> Gson().toJson(device as RollerShutter)
        }
        return json
    }*/
}
