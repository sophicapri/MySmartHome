package com.example.mysmarthome.ui.userprofile

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.mysmarthome.databinding.EditAddressAlertDialogBinding
import com.example.mysmarthome.model.User

class EditAddressAlertDialog(
    context: Context, address: User.Address,
    private var onAddressEditedListener: OnAddressEditedListener
) : AlertDialog(context),
    DialogInterface.OnShowListener,
    DialogInterface.OnDismissListener {
    private var _binding: EditAddressAlertDialogBinding? = null
    private val binding: EditAddressAlertDialogBinding
        get() = _binding!!
/*    val dialog:AlertDialog

    init {
        dialog = buildDialog()
    }

    private fun buildDialog(): AlertDialog {
       // binding.
    }*/

  /*  fun updateAddress() {
        val address = User.Address()
        onAddressEditedListener.onAddressEdited()
    }*/
    override fun onShow(p0: DialogInterface?) {
        TODO("Not yet implemented")
    }

    override fun onDismiss(p0: DialogInterface?) {
        TODO("Not yet implemented")
    }

    interface OnAddressEditedListener {
        fun onAddressEdited(address: User.Address)
    }
}