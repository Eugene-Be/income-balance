package com.app.incomebalance.contracts

import android.content.Context

interface BaseContract {

    interface View{
        fun getViewContext(): Context
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun backScreen()
    }

    interface Router {
        fun exit()
    }
}