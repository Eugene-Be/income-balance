package com.app.incomebalance.contracts

import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.data.Transaction

interface TransactionListContract {

    interface View : BaseContract.View {
        fun showProgress()
        fun updateTransactionList(newList: List<Transaction>)
    }

    interface Presenter : BaseContract.Presenter {
        fun setType(transactionType: TransactionType)
        fun getTransactions()
        fun onAddTransactionPressed()
        fun onTransactionClicked(transaction: Transaction)
        fun deleteTransaction(transaction: Transaction)
    }
}