package com.app.incomebalance.view.transactions

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DeleteConfirmationDialog : DialogFragment() {


    interface ConfirmationDialogListener {
        fun confirmDelete(transactionId: Long)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = arguments?.getString(KEY,"")
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Подтвердите удаление")
            .setMessage(message)
            .setPositiveButton("DELETE") { _, _ ->
                childFragmentManager.setFragmentResult("CONFIRMATION",
                    Bundle().apply { putBoolean(KEY, true) })

            }
            .setNegativeButton("CANCEL") { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }


    companion object {
        const val KEY = "TRANSACTION"
        fun newInstance(transactionString: String): DeleteConfirmationDialog {
            val fragment = DeleteConfirmationDialog()
            fragment.arguments = Bundle().apply { putString(KEY, transactionString) }
            return fragment
        }
    }
}
