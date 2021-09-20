package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mysmarthome.R
import com.example.mysmarthome.databinding.EditNameDialogBinding
import com.example.mysmarthome.model.User
import com.google.android.material.textfield.TextInputEditText

class EditNameDialogFragment(
    private var user: User,
    private var onNameEditedListener: OnNameEditedListener
) : DialogFragment() {
    private var _binding: EditNameDialogBinding? = null
    private val binding: EditNameDialogBinding
        get() = _binding!!
    private var validInput = true


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertBuilder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
        val inflater = layoutInflater
        _binding = EditNameDialogBinding.inflate(inflater)
        bindViews()
        val view = inflater.inflate(R.layout.title_edit_name_dialog, null)
        val dialogBuilder = alertBuilder.setCustomTitle(view)
            .setView(binding.root)
            .setPositiveButton(getString(R.string.save)) { _, _ -> updateName() }
            .setNegativeButton(getString(android.R.string.cancel)) { _, _ -> this.dismiss() }
            .setOnDismissListener(this)
        return dialogBuilder.create()
    }

    private fun bindViews() {
        binding.firstNameInput.setText(user.firstName)
        binding.lastNameInput.setText(user.lastName)
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
                this@EditNameDialogFragment.dismiss()
            }
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

    companion object{
        const val TAG = "EditNameDialog"
    }

    interface OnNameEditedListener {
        fun onNameEdited(user: User)
    }
}