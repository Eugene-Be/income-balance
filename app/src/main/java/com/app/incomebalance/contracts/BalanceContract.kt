package com.app.incomebalance.contracts

import com.app.incomebalance.domain.model.Balance

interface BalanceContract {

    interface View : BaseContract.View {
        fun showBalance(balance: Balance)
    }

    interface Presenter : BaseContract.Presenter {
        fun updateBalance()
    }
}