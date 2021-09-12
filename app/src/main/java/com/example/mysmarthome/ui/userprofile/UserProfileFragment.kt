package com.example.mysmarthome.ui.userprofile

import androidx.fragment.app.Fragment
import com.example.mysmarthome.databinding.UserprofileFragmentBinding

class UserProfileFragment : Fragment() {
    private var _binding : UserprofileFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}