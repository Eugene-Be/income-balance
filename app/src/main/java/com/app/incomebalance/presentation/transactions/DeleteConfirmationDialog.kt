package com.app.incomebalance.presentation.transactions

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.app.incomebalance.R
import com.app.incomebalance.common.Constants.REQUEST_KEY
import com.app.incomebalance.common.Constants.TRANSACTION_KEY


class DeleteConfirmationDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val message = arguments?.getString(REQUEST_KEY,"")
        val builder = AlertDialog.Builder(requireActivity())

        builder.setTitle(getString(R.string.confirm_delete))
            .setMessage(message)
            .setPositiveButton(R.string.delete) { _, _ ->
                parentFragmentManager.setFragmentResult(REQUEST_KEY,
                    Bundle().apply { putBoolean(REQUEST_KEY, true) })
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }

    companion object {
        fun newInstance(transactionString: String): DeleteConfirmationDialog {
            val fragment = DeleteConfirmationDialog()
            fragment.arguments = Bundle().apply { putString(TRANSACTION_KEY, transactionString) }
            return fragment
        }
    }
}
