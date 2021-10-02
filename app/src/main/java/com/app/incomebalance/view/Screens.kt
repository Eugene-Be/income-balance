package com.app.incomebalance.view

import com.app.incomebalance.view.transactionEdit.EditTransactionFragment

object Screens {

    fun editTransactionScreen(transactionId: Long) {
        EditTransactionFragment.newInstance(transactionId)
    }

    fun createTransactionScreen(transactionId: Long) {
        EditTransactionFragment.newInstance(transactionId)
    }

}