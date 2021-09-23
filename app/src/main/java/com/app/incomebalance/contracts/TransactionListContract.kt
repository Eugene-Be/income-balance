package com.app.incomebalance.contracts

import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.TransactionType

interface TransactionListContract {

    interface View : BaseContract.View {
        fun showTransactionList(transactions: List<Transaction>)
    }

    interface Presenter : BaseContract.Presenter {
        fun addTransaction(transactionType: TransactionType)
        fun editTransaction(transaction: Transaction)
        fun deleteTransaction(transaction: Transaction)
    }

    interface Router : BaseContract.Router {
        fun showCreateTransactionScreen(transactionType: TransactionType)
        fun showEditTransactionScreen(transaction: Transaction)
    }
}