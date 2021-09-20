package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.EditAddressAlertDialogBinding
import com.example.mysmarthome.model.User
import com.google.android.material.textfield.TextInputEditText

class EditAddressDialogFragment(
    private var address: User.Address,
    private var onAddressEditedListener: EditAddressAlertDialog.OnAddressEditedListener
) : DialogFragment(){
    private var _binding: EditAddressAlertDialogBinding? = null
    private val binding: EditAddressAlertDialogBinding
        get() = _binding!!
    private var validInput = true

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertBuilder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.title_edit_address_dialog, null)
        _binding = EditAddressAlertDialogBinding.inflate(inflater)
        bindViews()
        val dialogBuilder = alertBuilder.setCustomTitle(view)
            .setView(binding.root)
            .setPositiveButton(getString(R.string.save)) { _, _ -> updateAddress() }
            .setNegativeButton(getString(android.R.string.cancel)) { _, _ ->  this.dismiss() }
            .setOnDismissListener(this)
        //dialog.setOnShowListener(this)
        return dialogBuilder.create()
    }

    private fun updateAddress() {
        binding.apply {
            validInput = true
            if (streetCodeInput.text.isNullOrEmpty())
                showError(binding.streetInput)
            if (streetInput.text.isNullOrEmpty())
                showError(binding.streetInput)
            if (postalCodeInput.text.isNullOrEmpty())
                showError(binding.postalCodeInput)
            if (cityInput.text.isNullOrEmpty())
                showError(binding.cityInput)
            if (countryInput.text.isNullOrEmpty())
                showError(binding.countryInput)

            if (validInput) {
                address.country = countryInput.text.toString()
                address.city = cityInput.text.toString()
                address.postalCode = postalCodeInput.text.toString().toInt()
                address.street = streetInput.text.toString()
                address.streetCode = streetCodeInput.text.toString()
                onAddressEditedListener.onAddressEdited(address)
                this@EditAddressDialogFragment.dismiss()
            }
        }
    }

    private fun bindViews() {
        binding.apply {
            streetInput.setText(address.street)
            streetCodeInput.setText(address.streetCode)
            postalCodeInput.setText(address.postalCode.toString())
            cityInput.setText(address.city)
            countryInput.setText(address.country)
        }
    }

    private fun showError(editText: TextInputEditText) {
        editText.error = getString(R.string.empty_field)
        validInput = false
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        _binding = null
    }


    companion object {
        const val TAG = "EditAddressDialog"
    }

}