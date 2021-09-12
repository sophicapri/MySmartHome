package com.example.mysmarthome.ui.device.rollershutter

import androidx.fragment.app.Fragment
import com.example.mysmarthome.databinding.RollershutterDetailFragmentBinding

class RollerShutterDetailFragment : Fragment() {
    private var _binding : RollershutterDetailFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}