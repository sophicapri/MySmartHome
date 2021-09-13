package com.example.mysmarthome.ui.device.light

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysmarthome.databinding.LightDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LightDetailFragment : Fragment() {
    private var _binding: LightDetailFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LightDetailFragmentBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        val light = LightDetailFragmentArgs.fromBundle(requireArguments()).light
        binding.areaName.text = light.deviceName
        binding.toolbar.apply {
            setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
            title = light.productType.value
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}