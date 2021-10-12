package com.app.incomebalance.presentation

import com.app.incomebalance.common.Screens
import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.contracts.MainContract
import com.github.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter @Inject constructor(router: Router) : BasePresenter(), MainContract.Presenter {
    init {
        _router = router
    }

    override fun attachView(view: BaseContract.View) {
        super.attachView(view)
        (_router as Router).newRootScreen(Screens.ViewPagerScreen())
    }
}