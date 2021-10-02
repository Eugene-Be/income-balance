package com.app.incomebalance.view.transactionEdit


import com.app.incomebalance.contracts.BaseEditTransactionContract

import com.app.incomebalance.presenters.BasePresenter


abstract class BaseEditTransactionPresenter () :
    BasePresenter(), BaseEditTransactionContract.Presenter {

    override fun cancel() {
        _router?.exit()
    }
}
