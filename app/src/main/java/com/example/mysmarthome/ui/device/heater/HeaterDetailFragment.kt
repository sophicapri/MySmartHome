package com.example.mysmarthome.ui.device.heater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.HeaterDetailFragmentBinding
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.Heater
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeaterDetailFragment : Fragment() {
    private var _binding: HeaterDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var heater: Heater
    private val viewModel by viewModels<HeaterDetailVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HeaterDetailFragmentBinding.inflate(inflater, container, false)
        bindViews()
        addListeners()
        return binding.root
    }

    private fun bindViews() {
        heater = HeaterDetailFragmentArgs.fromBundle(requireArguments()).heater
        binding.apply {
            areaName.text = heater.deviceName
            temperatureValue.text = heater.temperature.toString()
            sliderTemperature.value = heater.temperature
            switchDeviceMode.apply {
                isChecked = heater.mode != DeviceMode.OFF
            }
            toolbar.apply {
                setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
                title = getString(R.string.heater_title)
            }
        }
    }

    private fun addListeners() {
        binding.apply {
            sliderTemperature.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    heater.temperature = slider.value
                    viewModel.updateHeater(heater)
                }
            })

            sliderTemperature.addOnChangeListener { _, value, _ ->
                temperatureValue.text = value.toString()
            }
            switchDeviceMode.setOnCheckedChangeListener { _, _ ->
                if (switchDeviceMode.isChecked)
                    heater.mode = DeviceMode.ON
                else
                    heater.mode = DeviceMode.OFF
                viewModel.updateHeater(heater)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}