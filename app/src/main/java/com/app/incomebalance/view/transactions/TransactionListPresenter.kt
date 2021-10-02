package com.app.incomebalance.view.transactions

import com.app.incomebalance.R
import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.contracts.TransactionListContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.data.TransactionRepository
import com.app.incomebalance.domain.TransactionType
import com.app.incomebalance.presenters.BasePresenter
import com.app.incomebalance.view.transactionEdit.CreateTransactionFragment
import com.app.incomebalance.view.transactionEdit.EditTransactionFragment
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class TransactionListPresenter @Inject constructor(val repository: TransactionRepository) :
    BasePresenter(), TransactionListContract.Presenter {
    val view get() = _view as TransactionListContract.View?
    val router get() = _router as TransactionListRouter?
//    val repository: TransactionRepository =

    override fun attachView(view: BaseContract.View) {
        super.attachView(view)
        _router = TransactionListRouter(view as TransactionListContract.View)
    }

    override fun addTransaction(transactionType: TransactionType) {
        router?.showCreateTransactionScreen(transactionType)
    }

    override fun editTransaction(transaction: Transaction) {
        router?.showEditTransactionScreen(transaction)
    }

    override fun deleteTransaction(transactionId: Long) {
        runBlocking {
            async {
                repository.delete(transactionId)
            }
        }
    }

    class TransactionListRouter(val view: TransactionListContract.View) :
        TransactionListContract.Router,
        BaseRouter(view) {
        override fun showCreateTransactionScreen(transactionType: TransactionType) {
            val createTransactionFragment =
                CreateTransactionFragment.newInstance(transactionType.ordinal)

            view.getViewFragmentManager()!!.beginTransaction()
                .replace(R.id.fragment_container, createTransactionFragment, "editTransaction")
                .addToBackStack(null).commit()
        }

        override fun showEditTransactionScreen(transaction: Transaction) {
            val editTransactionFragment = EditTransactionFragment.newInstance(transaction.id)
            view.getViewFragmentManager()!!.beginTransaction()
                .add(R.id.fragment_container, editTransactionFragment, "editTransaction")
                .addToBackStack(null).commit()
        }
    }
}