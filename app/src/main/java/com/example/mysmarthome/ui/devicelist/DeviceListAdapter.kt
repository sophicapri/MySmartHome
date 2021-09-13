package com.example.mysmarthome.ui.devicelist

import android.content.res.Resources
import android.graphics.ColorFilter
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
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
import android.widget.Toast

import android.app.ListActivity

import androidx.recyclerview.widget.ItemTouchHelper




class DeviceListAdapter(var onDeviceLongClickListener: OnDeviceLongClickListener) : ListAdapter<Device, DeviceListAdapter.DeviceViewHolder>(DIFF_CALLBACK) {
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
            binding.deviceContainer.setOnLongClickListener {
                binding.deviceChecked.visibility = View.VISIBLE
                binding.deviceContainer.setCardBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.black
                    )
                )
                onDeviceLongClickListener.onDeviceLongClick(layoutPosition)
                editMode = true
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

interface OnDeviceLongClickListener {
    fun onDeviceLongClick(position: Int)
}
