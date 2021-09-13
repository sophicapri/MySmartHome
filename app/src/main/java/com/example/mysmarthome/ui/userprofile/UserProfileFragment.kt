package com.example.mysmarthome.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mysmarthome.databinding.UserProfileFragmentBinding

class UserProfileFragment : Fragment() {
    private var _binding: UserProfileFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        binding.toolbar.apply {
            setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}