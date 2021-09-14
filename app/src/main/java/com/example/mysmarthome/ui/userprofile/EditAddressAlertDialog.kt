package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.EditAddressAlertDialogBinding
import com.example.mysmarthome.databinding.EditNameAlertDialogBinding
import com.example.mysmarthome.model.User
import com.google.android.material.textfield.TextInputEditText

class EditAddressAlertDialog(
    context: Context, private var address: User.Address,
    private var onAddressEditedListener: OnAddressEditedListener
) : AlertDialog(context),
    DialogInterface.OnShowListener,
    DialogInterface.OnDismissListener {
    private var _binding: EditAddressAlertDialogBinding? = null
    private val binding: EditAddressAlertDialogBinding
        get() = _binding!!
    val dialog: AlertDialog
    private var validInput = true

    init {
        dialog = buildDialog()
    }

    private fun buildDialog(): AlertDialog {
        val alertBuilder = Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.title_edit_address_dialog, null)
        _binding = EditAddressAlertDialogBinding.inflate(inflater)
        bindViews()
        val dialogBuilder = alertBuilder.setCustomTitle(view)
            .setView(binding.root)
            .setPositiveButton(context.getString(R.string.save), null)
            .setNegativeButton(context.getString(android.R.string.cancel), null)
            .setOnDismissListener(this)
        val dialog = dialogBuilder.create()
        dialog.setOnShowListener(this)
        return dialog
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

    override fun onShow(p0: DialogInterface?) {
        val positiveButton = dialog.getButton(BUTTON_POSITIVE)
        positiveButton.setOnClickListener { updateAddress() }
        val negativeButton = dialog.getButton(BUTTON_NEGATIVE)
        negativeButton.setOnClickListener { dialog.dismiss() }
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
                dialog.dismiss()
            }
        }
    }

    private fun showError(editText: TextInputEditText) {
        editText.error = context.getString(R.string.empty_field)
        validInput = false
    }

    override fun onDismiss(p0: DialogInterface?) {
        _binding = null
    }

    interface OnAddressEditedListener {
        fun onAddressEdited(address: User.Address)
    }
}