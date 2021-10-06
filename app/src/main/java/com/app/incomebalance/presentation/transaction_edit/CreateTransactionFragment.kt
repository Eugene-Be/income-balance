package com.app.incomebalance.presentation.transaction_edit


import android.os.Bundle
import android.view.ViewGroup
import com.app.incomebalance.R
import com.app.incomebalance.common.Constants.TYPE_NUMBER_KEY
import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.FragmentEditTransactionBinding

class CreateTransactionFragment : CreateEditBaseFragment() {
    private var transactionType: TransactionType = TransactionType.INCOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val typeNumber = it.getInt(TYPE_NUMBER_KEY)
            transactionType = TransactionType.values()[typeNumber]
            transaction = Transaction(transactionType = transactionType)
        }
    }

    override fun showTransactionText() {
        binding.editTransactionTitle.text = getString(R.string.create_transaction_title)
    }

    override fun getBinding(container: ViewGroup?): FragmentEditTransactionBinding {
        return FragmentEditTransactionBinding.inflate(layoutInflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(transactionTypeNumber: Int) =
            CreateTransactionFragment().apply {
                arguments = Bundle().apply {
                    putInt(TYPE_NUMBER_KEY, transactionTypeNumber)
                }
            }
    }
}