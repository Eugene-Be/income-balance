@file:Suppress("PropertyName")

package com.app.incomebalance.presentation


import android.content.Context
import com.app.incomebalance.common.DispatcherProvider
import com.app.incomebalance.common.appComponent
import com.app.incomebalance.contracts.BaseContract
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

abstract class BasePresenter : BaseContract.Presenter {
    var _view: BaseContract.View? = null

    @set:Inject
    var router: Router? = null

    @Inject
    lateinit var dispatchers: DispatcherProvider

    internal val presenterScope: CoroutineScope by lazy { CoroutineScope(dispatchers.default) }

    override fun attachView(view: BaseContract.View) {
        if (view is Context) {

            view.appComponent.inject(this)
        }
        this._view = view
    }

    override fun detachView() {
        _view = null
        router = null
    }
}

