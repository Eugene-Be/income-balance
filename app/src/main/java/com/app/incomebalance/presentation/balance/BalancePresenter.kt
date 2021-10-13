package com.app.incomebalance.presentation.balance

import com.app.incomebalance.common.Resource
import com.app.incomebalance.contracts.BalanceContract
import com.app.incomebalance.domain.use_case.GetBalanceUseCase
import com.app.incomebalance.presentation.BasePresenter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject


class BalancePresenter @Inject constructor(private val getBalanceUseCase: GetBalanceUseCase) :
    BasePresenter(),
    BalanceContract.Presenter {
    val view get() = _view as BalanceContract.View?

    override fun updateBalance() {
        getBalanceUseCase.execute().onEach {
            withContext(dispatchers.main) {
                when (it) {
                    is Resource.Error -> view?.showMessage(it.message!!)
                    is Resource.Loading -> {}
                    is Resource.Success -> view?.showBalance(it.data!!)
                }
            }
        }.launchIn(presenterScope)
    }
}