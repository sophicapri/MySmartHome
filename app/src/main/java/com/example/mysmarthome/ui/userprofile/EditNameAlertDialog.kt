package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.EditNameAlertDialogBinding
import com.example.mysmarthome.model.User
import com.google.android.material.textfield.TextInputEditText

class EditNameAlertDialog(
    context: Context, private var user: User,
    private var onNameEditedListener: OnNameEditedListener
) : AlertDialog(context),
    DialogInterface.OnShowListener,
    DialogInterface.OnDismissListener {
    private var _binding: EditNameAlertDialogBinding? = null
    private val binding: EditNameAlertDialogBinding
        get() = _binding!!
    val dialog: AlertDialog
    private var validInput = true


    init {
        dialog = buildDialog()
    }

    private fun buildDialog(): AlertDialog {
        val alertBuilder = Builder(context, R.style.Theme_MaterialComponents_Dialog_Alert)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.title_edit_name_dialog, null)
        _binding = EditNameAlertDialogBinding.inflate(inflater)
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
        binding.firstNameInput.setText(user.firstName)
        binding.lastNameInput.setText(user.lastName)
    }


    override fun onShow(p0: DialogInterface?) {
        val positiveButton = dialog.getButton(BUTTON_POSITIVE)
        positiveButton.setOnClickListener { updateName() }
        val negativeButton = dialog.getButton(BUTTON_NEGATIVE)
        negativeButton.setOnClickListener { dialog.dismiss() }
    }

    private fun updateName() {
        validInput = true
        binding.apply {
            if (firstNameInput.text.isNullOrEmpty() || lastNameInput.text.isNullOrEmpty()) {
                if (firstNameInput.text.toString().isEmpty()) {
                    showError(binding.firstNameInput)
                }
                if (lastNameInput.text.toString().isEmpty()) {
                    showError(binding.lastNameInput)
                }
            }
            if (validInput) {
                user.firstName = firstNameInput.text.toString().trim()
                user.lastName = lastNameInput.text.toString().trim()
                onNameEditedListener.onNameEdited(user)
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

    interface OnNameEditedListener {
        fun onNameEdited(user: User)
    }
}