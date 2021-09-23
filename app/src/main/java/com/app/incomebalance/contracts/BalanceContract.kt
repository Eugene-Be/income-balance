package com.app.incomebalance.contracts

import com.app.incomebalance.domain.Balance

interface BalanceContract {

    interface View : BaseContract.View {
        fun showBalance(balance: Balance)
    }

    interface Presenter : BaseContract.Presenter {
        fun updateBalance()
    }

    interface Router : BaseContract.Router
}