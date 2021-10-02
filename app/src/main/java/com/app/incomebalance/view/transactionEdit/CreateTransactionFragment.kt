package com.app.incomebalance.view.transactionEdit
//TODO(transactionEdit с маленькой буквы все слова????)

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.incomebalance.R
import com.app.incomebalance.appComponent
import com.app.incomebalance.domain.TransactionType
import com.app.incomebalance.view.BaseFragment

private const val TRANSACTION_TYPE_NUMBER_KEY = "TRANSACTION_TYPE_NUMBER_KEY"

class CreateTransactionFragment : BaseFragment() {
    private var transactionType: TransactionType = TransactionType.INCOME

    lateinit var presenter: CreateTransactionPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("EditTransFragmentTag", "onAttach:")

        context.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val typeNumber = it.getInt(TRANSACTION_TYPE_NUMBER_KEY)
            transactionType = TransactionType.values()[typeNumber]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_transaction, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(transactionTypeNumber: Int) =
            EditTransactionFragment().apply {
                arguments = Bundle().apply {
                    putInt(TRANSACTION_TYPE_NUMBER_KEY, transactionTypeNumber)
                }
            }
    }
}