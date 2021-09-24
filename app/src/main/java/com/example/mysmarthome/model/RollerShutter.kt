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
data class RollerShutter(
    override val id: Int,
    val deviceName: String,
    var position: Int
) : Device(id, ProductType.ROLLER_SHUTTER), EntityMaker, Parcelable {

    override fun toDeviceEntity() = DeviceEntity(
        id = id, deviceName = deviceName, productType = productType,
        position = position, temperature = null, mode = null, intensity = null)

    override fun bindDevice(binding: DeviceItemBinding) {
        binding.apply {
            deviceText.text = this@RollerShutter.deviceName
            deviceIcon.setImageDrawable(
                ContextCompat.getDrawable(binding.root.context, R.drawable.ic_roller_shutter)
            )
            positionContainer.visibility = View.VISIBLE
            positionValue.text = this@RollerShutter.position.toString()
        }

    }
}