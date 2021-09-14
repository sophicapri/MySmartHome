package com.example.mysmarthome.ui.userprofile

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mysmarthome.databinding.UserProfileFragmentBinding
import com.example.mysmarthome.model.User
import com.google.android.material.switchmaterial.SwitchMaterial
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.util.*

@AndroidEntryPoint
class UserProfileFragment : Fragment(), EditAddressAlertDialog.OnAddressEditedListener,
    DatePickerDialog.OnDateSetListener {
    private var _binding: UserProfileFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserProfileVM>()
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserProfileFragmentBinding.inflate(inflater, container, false)
        addListeners()
        viewModel.user.observe(viewLifecycleOwner) { userDb ->
            user = userDb
            bindUserData()
        }
        return binding.root
    }

    private fun bindUserData() {
        val df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
        //binding.birthDate.text = df.format(birthDate)

        viewModel.currentTheme.observe(viewLifecycleOwner) {
            binding.switchDarkMode.isChecked = it
        }
    }

    private fun addListeners() {
        binding.toolbar.apply {
            setNavigationOnClickListener { view -> view.findNavController().navigateUp() }
        }

        binding.switchDarkMode.setOnClickListener {
            viewModel.toggleNightMode().observe(viewLifecycleOwner) { themeChanged ->
                if (themeChanged)
                    findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToUserProfileFragment())
            }
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

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val birthDate: Date = GregorianCalendar(year, month, dayOfMonth).time
        user.birthDate = birthDate.time
        viewModel.updateUser(user)
    }
}