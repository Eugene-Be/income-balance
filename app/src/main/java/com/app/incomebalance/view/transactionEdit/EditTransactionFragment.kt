package com.app.incomebalance.view.transactionEdit
//TODO(transactionEdit с маленькой буквы все слова????)

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.incomebalance.R
import com.app.incomebalance.appComponent
import com.app.incomebalance.contracts.EditTransactionContract
import com.app.incomebalance.view.BaseFragment


private const val TRANSACTION_KEY = "TRANSACTION_KEY"

class EditTransactionFragment : BaseFragment() {
    private var transactionId: Long? = null

    lateinit var presenter: EditTransactionPresenter


    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.i("EditTransFragmentTag", "onAttach:")

        context.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            transactionId = it.getLong(TRANSACTION_KEY)
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
        fun newInstance(transactionId: Long) =
            EditTransactionFragment().apply {
                arguments = Bundle().apply {
                    putLong(TRANSACTION_KEY, transactionId)
                }
            }
    }
}