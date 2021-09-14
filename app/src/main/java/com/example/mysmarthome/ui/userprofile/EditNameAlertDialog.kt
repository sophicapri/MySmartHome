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
        //val view = inflater.inflate(R.layout.title_filter_dialog, null)
        _binding = EditNameAlertDialogBinding.inflate(inflater)
        //bindFilterViews()
        val dialogBuilder = alertBuilder//.setCustomTitle(view)
            .setView(binding.root)
            .setPositiveButton(context.getString(R.string.save), null)
            .setNegativeButton(context.getString(android.R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .setOnDismissListener(this)
        val dialog = dialogBuilder.create()
        dialog.setOnShowListener(this)
        return dialog
    }

    fun updateName() {
        //onNameEditedListener.onNameEdited()
    }

    override fun onShow(p0: DialogInterface?) {
    }

    override fun onDismiss(p0: DialogInterface?) {
        _binding = null
    }

    interface OnNameEditedListener {
        fun onNameEdited(user: User)
    }
}