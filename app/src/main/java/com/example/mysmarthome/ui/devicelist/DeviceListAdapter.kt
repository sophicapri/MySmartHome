package com.example.mysmarthome.ui.devicelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.DeviceItemBinding
import com.example.mysmarthome.model.Device
import com.example.mysmarthome.model.Heater
import com.example.mysmarthome.model.Light
import com.example.mysmarthome.model.RollerShutter


class DeviceListAdapter(var onDeviceClickListener: OnDeviceClickListener) :
    ListAdapter<Device, DeviceListAdapter.DeviceViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(
            DeviceItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = getItem(position)
        holder.bindTo(device)
    }


    inner class DeviceViewHolder(private val binding: DeviceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(device: Device) {
            itemView.setOnClickListener {
                onDeviceClickListener.onDeviceClick(device)
            }
            binding.deviceModeContainer.visibility = View.GONE
            binding.positionContainer.visibility = View.GONE

            when (device) {
                is Light -> device.bindDevice(binding)
                is Heater -> device.bindDevice(binding)
                is RollerShutter -> device.bindDevice(binding)
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

    interface OnDeviceClickListener {
        fun onDeviceClick(device: Device)
    }
}
