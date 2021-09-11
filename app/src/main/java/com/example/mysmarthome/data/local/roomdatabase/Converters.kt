package com.example.mysmarthome.data.local.roomdatabase

import androidx.room.TypeConverter
import com.example.mysmarthome.model.Address
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.ProductType
import com.google.gson.Gson

/*
*  I use Gson and not Moshi for simplicity just for this particular case, but it's probably
*  best to stick with one tool.
 */
class Converters {

    @TypeConverter
    fun addressToJson(address: Address?): String = Gson().toJson(address)

    @TypeConverter
    fun jsonToAddress(json: String): Address = Gson().fromJson(json, Address::class.java)

    @TypeConverter
    fun deviceModeToJson(deviceMode: DeviceMode?): String = Gson().toJson(deviceMode)

    @TypeConverter
    fun jsonToDeviceMode(json: String) : DeviceMode = Gson().fromJson(json, DeviceMode::class.java)

    @TypeConverter
    fun productTypeToJson(productType: ProductType?): String = Gson().toJson(productType)

    @TypeConverter
    fun jsonToProductType(json: String) : ProductType = Gson().fromJson(json, ProductType::class.java)

    @TypeConverter
    fun jsonToDevice(json: String){

    }

}