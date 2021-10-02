package com.app.incomebalance.presenters

import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.data.TransactionRepository
import javax.inject.Inject

open class BasePresenter () : BaseContract.Presenter {
    var _view: BaseContract.View? = null
    var _router: BaseContract.Router? = null

    override fun attachView(view: BaseContract.View) {
        this._view = view
    }

    override fun detachView() {
        this._view = null
    }

    override fun backScreen() {
        _router?.exit()
    }

    open class BaseRouter(view: BaseContract.View) : BaseContract.Router {
        override fun exit() {
            TODO("Not yet implemented")
        }
    }
}

