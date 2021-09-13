package com.example.mysmarthome.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mysmarthome.databinding.UserProfileFragmentBinding
import com.example.mysmarthome.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment(), EditAddressAlertDialog.OnAddressEditedListener {
    private var _binding: UserProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserProfileVM>()
    private lateinit var user : User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        bindViews()
        viewModel.user.observe(viewLifecycleOwner){ userDb -> user = userDb }
        return binding.root
    }

    private fun bindViews() {
        binding.toolbar.apply {
            setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
        }

        binding.changeTheme.setOnClickListener {
            //AppCompatDelegate.MODE_NIGHT_YES
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToUserProfileFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAddressEdited(address: User.Address) {
        user.address = address
        viewModel.updateUser(user)
    }
}