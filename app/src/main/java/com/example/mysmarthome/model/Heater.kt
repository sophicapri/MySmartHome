package com.example.mysmarthome.model

import android.os.Parcelable
import android.view.View
import androidx.core.content.ContextCompat
import com.example.mysmarthome.R
import com.example.mysmarthome.data.local.roomdatabase.DeviceEntity
import com.example.mysmarthome.data.local.roomdatabase.EntityMaker
import com.example.mysmarthome.databinding.DeviceItemBinding
import kotlinx.parcelize.Parcelize

@Parcelize
data class Heater(
    override val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var temperature: Float
) : Device(id, ProductType.HEATER), EntityMaker, Device.DeviceBinder, Parcelable {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, temperature = temperature,
        productType = productType, intensity = null, position = null
    )

    override fun bindDevice(binding: DeviceItemBinding) {
        binding.apply {
            deviceText.text = this@Heater.deviceName
            deviceIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_heater)
            )
            deviceModeContainer.visibility = View.VISIBLE
            deviceModeValue.text = this@Heater.mode.name
        }
    }
}


