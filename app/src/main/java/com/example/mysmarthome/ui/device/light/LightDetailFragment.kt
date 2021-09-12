package com.example.mysmarthome.ui.device.light

import androidx.fragment.app.Fragment
import com.example.mysmarthome.databinding.LightDetailFragmentBinding

class LightDetailFragment : Fragment() {

    private var _binding : LightDetailFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}