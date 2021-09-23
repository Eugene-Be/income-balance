package com.app.incomebalance.contracts

interface CreateTransactionContract {

    interface View : BaseEditTransactionContract.View

    interface Presenter : BaseEditTransactionContract.Presenter

    interface Router : BaseEditTransactionContract.Router
}