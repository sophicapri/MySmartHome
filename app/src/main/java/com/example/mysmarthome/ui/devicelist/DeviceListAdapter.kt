package com.example.mysmarthome.ui.devicelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mysmarthome.databinding.DeviceItemBinding
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.RollerShutter

class DeviceListAdapter() : ListAdapter<Device, DeviceListAdapter.DeviceViewHolder>(DIFF_CALLBACK) {
    var editMode = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            DeviceItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device: Device? = getItem(position)
        holder.bindTo(device)
    }


    inner class DeviceViewHolder(private val binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.artistImage.setOnLongClickListener {
                editMode = true
                binding.tint.visibility = View.VISIBLE
                true
            }
        }

        fun bindTo(device: Device?) {
            binding.apply {
                when (device) {
                    is Light -> deviceText.text = device.deviceName
                    is Heater -> deviceText.text = device.deviceName
                    is RollerShutter -> deviceText.text = device.deviceName
                }
            }

        }
    }

     companion object {
         private val DIFF_CALLBACK = object :
             DiffUtil.ItemCallback<Device>() {

             override fun areItemsTheSame(
                 oldItem: Device,
                 newItem: Device
             ) = oldItem.id == newItem.id

             override fun areContentsTheSame(
                 oldItem: Device,
                 newItem: Device
             ) = oldItem == newItem
         }
     }
}

interface OnDeviceLongClickListener{
    fun onDeviceLongClick()
}
