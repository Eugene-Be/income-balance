package com.app.incomebalance.contracts

import com.app.incomebalance.data.Transaction

interface EditTransactionContract {

    interface View : BaseEditTransactionContract.View {
        fun showTransactionDetails(transaction: Transaction)
    }

    interface Presenter : BaseEditTransactionContract.Presenter

    interface Router : BaseEditTransactionContract.Router
}