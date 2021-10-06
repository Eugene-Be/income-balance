package com.app.incomebalance.contracts

import com.app.incomebalance.data.Transaction

interface BaseEditTransactionContract {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter {
        fun onSaveClicked(transaction: Transaction)
        fun onCancelClicked()
    }
}