package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.EditNameAlertDialogBinding
import com.example.mysmarthome.model.User

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
        binding.apply {
            var validInput = true
            if (firstNameInput.text.toString().isEmpty()) {
                firstNameInput.error = "This field cannot be empty"
                validInput = false
            }
            if (lastNameInput.text.toString().isEmpty()) {
                lastNameInput.error = "This field cannot be empty"
                validInput = false
            }
            if (validInput){
                user.firstName = firstNameInput.text.toString()
                user.lastName = lastNameInput.text.toString()
                onNameEditedListener.onNameEdited(user)
                dialog.dismiss()
            }
        }
    }

    override fun onDismiss(p0: DialogInterface?) {
        _binding = null
    }

    interface OnNameEditedListener {
        fun onNameEdited(user: User)
    }
}