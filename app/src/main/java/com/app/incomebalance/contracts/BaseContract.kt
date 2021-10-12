package com.app.incomebalance.contracts

interface BaseContract {

    interface View{
        fun showMessage(message: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}