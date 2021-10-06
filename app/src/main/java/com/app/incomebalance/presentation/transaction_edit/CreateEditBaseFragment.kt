package com.app.incomebalance.presentation.transaction_edit

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.app.incomebalance.common.appComponent
import com.app.incomebalance.contracts.BaseEditTransactionContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.FragmentEditTransactionBinding
import com.app.incomebalance.presentation.BaseFragment

abstract class CreateEditBaseFragment :
    BaseFragment<FragmentEditTransactionBinding, BaseEditTransactionContract.Presenter>(),
    BaseEditTransactionContract.View {
    var transaction: Transaction = Transaction()
    val nameView by lazy { binding.nameEditText }
    val valueView by lazy { binding.valueEditText }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showTransactionText()
        setupButtons()
    }

    abstract fun showTransactionText()

    private fun setupButtons() {
        binding.positiveButton.setOnClickListener {
            val newTransaction = createNewTransaction()
            presenter.onSaveClicked(newTransaction)
        }

        binding.negativeButton.setOnClickListener {
            presenter.onCancelClicked()
        }
    }

    private fun createNewTransaction() =
        Transaction(
            transaction.id,
            nameView.text.toString(),
            valueView.text.toDouble(),
            transaction.transactionType
        )
}

private fun Editable.toDouble(): Double {
    return if (this.isBlank()) {
        0.0
    } else {
        this.toString().toDouble()
    }
}
