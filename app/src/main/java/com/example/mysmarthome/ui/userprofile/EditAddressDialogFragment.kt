package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.EditAddressDialogBinding
import com.example.mysmarthome.databinding.TitleEditAddressDialogBinding
import com.example.mysmarthome.model.User
import com.google.android.material.textfield.TextInputEditText

class EditAddressDialogFragment(
    private var address: User.Address,
    private var onAddressEditedListener: OnAddressEditedListener
) : DialogFragment(), DialogInterface.OnShowListener{
    private var _binding: EditAddressDialogBinding? = null
    private val binding: EditAddressDialogBinding
        get() = _binding!!
    private var validInput = true

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertBuilder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
        val inflater = layoutInflater
        _binding = EditAddressDialogBinding.inflate(inflater)
        bindViews()
        val titleBinding = TitleEditAddressDialogBinding.inflate(layoutInflater)
        val dialogBuilder = alertBuilder.setCustomTitle(titleBinding.root)
            .setView(binding.root)
            .setPositiveButton(getString(R.string.save), null)
            .setNegativeButton(getString(android.R.string.cancel), null)
            .setOnDismissListener(this)
        val dialog = dialogBuilder.create()
        dialog.setOnShowListener(this)
        return dialog
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

    override fun onShow(dialogInterface: DialogInterface?) {
        val positiveButton = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener { updateAddress() }
        val negativeButton = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_NEGATIVE)
        negativeButton.setOnClickListener { this.dismiss() }
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

    interface OnAddressEditedListener {
        fun onAddressEdited(address: User.Address)
    }
}