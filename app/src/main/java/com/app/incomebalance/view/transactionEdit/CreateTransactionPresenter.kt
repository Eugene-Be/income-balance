package com.app.incomebalance.view.transactionEdit

import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.contracts.CreateTransactionContract
import com.app.incomebalance.contracts.EditTransactionContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.data.TransactionRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class CreateTransactionPresenter @Inject constructor(private val repository: TransactionRepository) :
    BaseEditTransactionPresenter() {

    override fun attachView(view: BaseContract.View) {
        super.attachView(view)
        _router = BaseRouter(view)
    }

    override fun saveTransaction(transaction: Transaction) {
        runBlocking {
            launch {
                repository.create(transaction)
            }
        }

    }

}
