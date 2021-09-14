package com.example.mysmarthome.ui.device.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mysmarthome.databinding.LightDetailFragmentBinding
import com.example.mysmarthome.model.DeviceMode
import com.example.mysmarthome.model.Light
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LightDetailFragment : Fragment() {
    private var _binding: LightDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<LightDetailVM>()
    private lateinit var light: Light


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LightDetailFragmentBinding.inflate(inflater, container, false)
        bindViews()
        addListeners()
        return binding.root
    }

    private fun bindViews() {
        light = LightDetailFragmentArgs.fromBundle(requireArguments()).light
        binding.apply {
            areaName.text = light.deviceName
            intensityValue.text = light.intensity.toString()
            sliderIntensity.value = light.intensity.toFloat()
            switchDeviceMode.apply {
                isChecked = light.mode != DeviceMode.OFF
            }
            toolbar.apply {
                setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
                title = light.productType.value
            }
        }
    }

    private fun addListeners() {
     binding.apply {
         sliderIntensity.addOnSliderTouchListener(object : Slider.OnSliderTouchListener{
             override fun onStartTrackingTouch(slider: Slider) {
             }

             override fun onStopTrackingTouch(slider: Slider) {
                 light.intensity = slider.value.toInt()
                 viewModel.updateLight(light)
             }
         } )
         sliderIntensity.addOnChangeListener { _, value, _ ->
             intensityValue.text = value.toInt().toString()
         }
         switchDeviceMode.setOnCheckedChangeListener { _, _ ->
             if(switchDeviceMode.isChecked)
                 light.mode = DeviceMode.ON
             else
                 light.mode = DeviceMode.OFF
             viewModel.updateLight(light)
         }
     }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}