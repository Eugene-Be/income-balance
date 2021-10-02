package com.app.incomebalance.contracts

import androidx.fragment.app.FragmentManager
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.TransactionType

interface TransactionListContract {

    interface View : BaseContract.View {
        fun getViewFragmentManager(): FragmentManager?
        fun showTransactionList(transactions: List<Transaction>)
    }

    interface Presenter : BaseContract.Presenter {
        fun addTransaction(transactionType: TransactionType)
        fun editTransaction(transaction: Transaction)
        fun deleteTransaction(transactionId: Long)
    }

    interface Router : BaseContract.Router {
        fun showCreateTransactionScreen(transactionType: TransactionType)
        fun showEditTransactionScreen(transaction: Transaction)
    }
}