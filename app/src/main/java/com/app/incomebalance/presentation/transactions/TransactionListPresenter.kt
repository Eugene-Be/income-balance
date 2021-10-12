package com.app.incomebalance.presentation.transactions

import com.app.incomebalance.common.Resource
import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.contracts.TransactionListContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.use_case.DeleteTransactionUseCase
import com.app.incomebalance.domain.use_case.GetTransactionsUseCase
import com.app.incomebalance.presentation.BasePresenter
import com.app.incomebalance.common.Screens
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TransactionListPresenter @Inject constructor(
    private val deleteTransactionUseCase: DeleteTransactionUseCase,
    private val getTransactionsUseCase: GetTransactionsUseCase,
    router: Router?,
) : BasePresenter(), TransactionListContract.Presenter {

    init {
        _router = router
    }

    private var transactionType: TransactionType = TransactionType.INCOME
    val view get() = _view as TransactionListContract.View?


    override fun attachView(view: BaseContract.View) {
        super.attachView(view)
        getTransactions()
    }

    override fun setType(transactionType: TransactionType) {
        this.transactionType = transactionType
    }

    override fun getTransactions() {
        getTransactionsUseCase.execute(transactionType).onEach { result ->
            withContext(Dispatchers.Main) {
                when (result) {
                    is Resource.Error -> view?.showMessage(result.message!!)
                    is Resource.Loading -> view?.showProgress()
                    is Resource.Success -> view?.updateTransactionList(result.data!!)
                }
            }

        }.launchIn(presenterScope)
    }

    override fun deleteTransaction(transaction: Transaction) {
        deleteTransactionUseCase.execute(transaction).onEach {
            withContext(Dispatchers.Main) {
                when (it) {
                    is Resource.Error -> view?.showMessage(it.message!!)
                    is Resource.Loading -> view?.showProgress()
                    is Resource.Success -> getTransactions()
                }
            }

        }.launchIn(presenterScope)
    }

    override fun onAddTransactionPressed() {
        _router?.navigateTo(Screens.CreateTransactionScreen(transactionType))
    }

    override fun onTransactionClicked(transaction: Transaction) {
        _router?.navigateTo(Screens.EditTransactionScreen(transaction))
    }
}