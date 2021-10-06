package com.app.incomebalance.presentation.transaction_edit

import android.os.Bundle
import android.view.ViewGroup
import com.app.incomebalance.R
import com.app.incomebalance.common.Constants.TRANSACTION_KEY
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.FragmentEditTransactionBinding

class EditTransactionFragment : CreateEditBaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            transaction = it.getParcelable(TRANSACTION_KEY)!!
        }
    }

    override fun showTransactionText() {
        binding.editTransactionTitle.text = getString(R.string.edit_transaction_title)
        nameView.apply { setText(transaction.name) }
        valueView.apply { setText(transaction.value.toString()) }
    }

    override fun getBinding(container: ViewGroup?): FragmentEditTransactionBinding {
        return FragmentEditTransactionBinding.inflate(layoutInflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(transaction: Transaction) =
            EditTransactionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(TRANSACTION_KEY, transaction)
                }
            }
    }
}