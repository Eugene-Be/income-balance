package com.app.incomebalance.contracts

interface BaseContract {

    interface View

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun backScreen()
    }

    interface Router {
        fun exit()
    }
}