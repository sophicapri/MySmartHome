package com.example.mysmarthome.ui.device.heater

import androidx.fragment.app.Fragment
import com.example.mysmarthome.databinding.HeaterDetailFragmentBinding
import com.example.mysmarthome.databinding.RollershutterDetailFragmentBinding

class HeaterDetailFragment : Fragment() {

    private var _binding : HeaterDetailFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}