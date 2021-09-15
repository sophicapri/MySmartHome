package com.example.mysmarthome.ui.device.rollershutter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.RollerShutterDetailFragmentBinding
import com.example.mysmarthome.model.RollerShutter
import com.google.android.material.slider.Slider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RollerShutterDetailFragment : Fragment() {
    private var _binding : RollerShutterDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var rollerShutter : RollerShutter
    private val viewModel by viewModels<RollerShutterDetailVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RollerShutterDetailFragmentBinding.inflate(inflater, container, false)
        bindViews()
        addListeners()
        return binding.root
    }

    private fun bindViews() {
        rollerShutter = RollerShutterDetailFragmentArgs.fromBundle(requireArguments()).rollerShutter
        binding.apply {
            areaName.text = rollerShutter.deviceName
            positionValue.text = rollerShutter.position.toString()
            positionSlider.value = rollerShutter.position.toFloat()
            toolbar.apply {
                setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
                title = getString(R.string.roller_shutter_title)
            }
        }
    }

    private fun addListeners() {
        binding.apply {
            positionSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    rollerShutter.position = slider.value.toInt()
                    viewModel.updateRollerShutter(rollerShutter)
                }
            })

            positionSlider.addOnChangeListener { _, value, _ ->
                positionValue.text = value.toInt().toString()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}