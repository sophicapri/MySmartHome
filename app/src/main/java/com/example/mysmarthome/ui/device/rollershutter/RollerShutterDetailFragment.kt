package com.example.mysmarthome.ui.device.rollershutter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysmarthome.databinding.RollerShutterDetailFragmentBinding

class RollerShutterDetailFragment : Fragment() {
    private var _binding : RollerShutterDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RollerShutterDetailFragmentBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        val rollerShutter = RollerShutterDetailFragmentArgs.fromBundle(requireArguments()).rollerShutter
        binding.areaName.text = rollerShutter.deviceName
        binding.toolbar.apply {
            setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
            title = rollerShutter.productType.value
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}