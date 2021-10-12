package com.app.incomebalance.presentation.transaction_edit

import com.app.incomebalance.common.Resource
import com.app.incomebalance.contracts.BaseEditTransactionContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.use_case.SaveTransactionUseCase
import com.app.incomebalance.presentation.BasePresenter
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject


class EditTransactionPresenter @Inject constructor(
    private val saveTransactionUseCase: SaveTransactionUseCase,
    router: Router?
) : BasePresenter(), BaseEditTransactionContract.Presenter {

    init {
        _router = router
    }

    val view get() = _view as BaseEditTransactionContract.View?

    override fun onSaveClicked(transaction: Transaction) {
        saveTransactionUseCase.execute(transaction).onEach {
            withContext(Dispatchers.Main) {
                when (it) {
                    is Resource.Error -> view?.showMessage(it.message!!)
                    is Resource.Loading -> {}
                    is Resource.Success -> _router?.exit()
                }
            }
        }.launchIn(presenterScope)
    }

    override fun onCancelClicked() {
        _router?.exit()
    }
}