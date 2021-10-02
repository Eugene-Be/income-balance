package com.app.incomebalance.view.balance

import com.app.incomebalance.appComponent
import com.app.incomebalance.contracts.BalanceContract
import com.app.incomebalance.contracts.BaseContract
import com.app.incomebalance.data.DatabaseTransactionRepository
import com.app.incomebalance.data.TransactionRepository
import com.app.incomebalance.domain.Balance
import com.app.incomebalance.presenters.BasePresenter
import kotlinx.coroutines.*
import javax.inject.Inject

class BalancePresenter @Inject constructor(val repository :TransactionRepository) : BasePresenter(), BalanceContract.Presenter {
    val view get() = _view as BalanceContract.View?


    override fun attachView(view: BaseContract.View) {
        super.attachView(view)

    }

    override fun updateBalance() {
        runBlocking {
            launch(Dispatchers.Default) {
                //delay(1000)
                val balance = repository.getBalance()
                view?.showBalance(balance)
            }
        }

        //view.showBalance(Balance(100.0, 120.0, 20.0))
    }


}