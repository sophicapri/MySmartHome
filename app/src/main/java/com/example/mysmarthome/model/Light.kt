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
data class Light(
    override val id: Int,
    val deviceName: String,
    var mode: DeviceMode,
    var intensity: Int
) : Device(id, ProductType.LIGHT), EntityMaker, Parcelable{

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName,
        mode = mode, productType = productType,
        intensity = intensity, position = null, temperature = null
    )

    override fun bindDevice(binding: DeviceItemBinding) {
        binding.apply {
            deviceText.text = this@Light.deviceName
            deviceIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_smart_bulb)
            )
            deviceModeValue.text = this@Light.mode.name
            deviceModeContainer.visibility = View.VISIBLE
        }
    }
}
