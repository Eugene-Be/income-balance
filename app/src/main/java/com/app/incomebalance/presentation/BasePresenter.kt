@file:Suppress("PropertyName")

package com.app.incomebalance.presentation


import com.app.incomebalance.contracts.BaseContract
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BasePresenter : BaseContract.Presenter {
    var _view: BaseContract.View? = null
    var _router:Router? = null

    internal val presenterScope: CoroutineScope = CoroutineScope(Dispatchers.Default)

    override fun attachView(view: BaseContract.View) {
        this._view = view
    }

    override fun detachView() {
        _view = null
        _router = null
    }
}

