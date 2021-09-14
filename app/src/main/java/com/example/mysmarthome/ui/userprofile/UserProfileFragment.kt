package com.example.mysmarthome.ui.userprofile

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.UserProfileFragmentBinding
import com.example.mysmarthome.model.User
import dagger.hilt.android.AndroidEntryPoint
import java.sql.Timestamp
import java.text.DateFormat
import java.util.*

@AndroidEntryPoint
class UserProfileFragment : Fragment(), EditAddressAlertDialog.OnAddressEditedListener,
    EditNameAlertDialog.OnNameEditedListener,
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
        viewModel.user.observe(viewLifecycleOwner) { userDb ->
            user = userDb
            bindData()
            addListeners()
        }
        return binding.root
    }

    private fun bindData() {
        binding.apply {
            userName.text = getString(R.string.user_name, user.firstName, user.lastName)
            val df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault())
            val stamp = Timestamp(user.birthDate)
            val date = Date(stamp.time)
            birthdate.text = df.format(date)
            address.text = getString(
                R.string.address,
                user.address.streetCode,
                user.address.street,
                user.address.postalCode.toString(),
                user.address.city,
                user.address.country
            )
        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
            binding.switchDarkMode.isChecked = false
        else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            binding.switchDarkMode.isChecked = true
    }

    private fun addListeners() {
        binding.apply {
            toolbar.setNavigationOnClickListener { view -> view.findNavController().navigateUp() }

            switchDarkMode.setOnCheckedChangeListener { _, _ ->
                viewModel.updateUserPrefs()

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                else if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                findNavController().navigate(UserProfileFragmentDirections.actionUserProfileFragmentToUserProfileFragment())

            }
            nameContainer.setOnClickListener {
                EditNameAlertDialog(requireContext(), user, this@UserProfileFragment).dialog.show()
            }
            birthdateContainer.setOnClickListener { showDatePickerDialog() }
            addressContainer.setOnClickListener {
                EditAddressAlertDialog(
                    requireContext(),
                    user.address, this@UserProfileFragment
                ).dialog.show()
            }
        }
    }

    // It would be best to show the birthdate in the datePickerDialog. Didn't have time to find out
    // know how to do it
    private fun showDatePickerDialog() {
        Locale.getDefault()
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            this, Calendar.getInstance()[Calendar.YEAR],
            Calendar.getInstance()[Calendar.MONTH],
            Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    override fun onNameEdited(user: User) {
        viewModel.updateUser(user)
    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val birthDate: Date = GregorianCalendar(year, month, dayOfMonth).time
        user.birthDate = birthDate.time
        viewModel.updateUser(user)
    }

    override fun onAddressEdited(address: User.Address) {
        user.address = address
        viewModel.updateUser(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}