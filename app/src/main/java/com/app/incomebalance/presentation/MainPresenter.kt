package com.app.incomebalance.presentation

import com.app.incomebalance.common.Screens
import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.contracts.MainContract
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter(), MainContract.Presenter {


    override fun attachView(view: BaseContract.View) {
        super.attachView(view)
        (router as Router).newRootScreen(Screens.ViewPagerScreen())
    }
}