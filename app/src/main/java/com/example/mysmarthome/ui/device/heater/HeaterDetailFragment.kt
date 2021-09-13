package com.example.mysmarthome.ui.device.heater

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysmarthome.databinding.HeaterDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeaterDetailFragment : Fragment() {

    private var _binding : HeaterDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HeaterDetailFragmentBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        val heater = HeaterDetailFragmentArgs.fromBundle(requireArguments()).heater
        binding.areaName.text = heater.deviceName
        binding.toolbar.apply {
            setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
            title = heater.productType.value
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}