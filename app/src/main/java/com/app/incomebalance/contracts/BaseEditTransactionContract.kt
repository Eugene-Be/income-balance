package com.app.incomebalance.contracts

import com.app.incomebalance.data.Transaction

interface BaseEditTransactionContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun saveTransaction(transaction: Transaction)
        fun cancel()
    }

    interface Router : BaseContract.Router
}