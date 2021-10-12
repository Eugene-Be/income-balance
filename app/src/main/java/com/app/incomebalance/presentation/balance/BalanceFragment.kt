package com.app.incomebalance.presentation.balance

import android.content.Context
import android.view.ViewGroup
import com.app.incomebalance.common.appComponent
import com.app.incomebalance.contracts.BalanceContract
import com.app.incomebalance.databinding.FragmentBalanceBinding
import com.app.incomebalance.domain.model.Balance
import com.app.incomebalance.presentation.BaseFragment

class BalanceFragment: BaseFragment<FragmentBalanceBinding,BalanceContract.Presenter>(), BalanceContract.View {

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
        presenter.updateBalance()
    }

    override fun showBalance(balance: Balance) {
        binding.balanceValueLabel.text = balance.balance.format(1)
        binding.incomeValueLabel.text = balance.income.format(1)
        binding.outcomeValueLabel.text = balance.outcome.format(1)
        binding.chart.update(balance)
    }

    override fun getBinding(container: ViewGroup?): FragmentBalanceBinding {
        return FragmentBalanceBinding.inflate(layoutInflater,container,false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BalanceFragment()
    }
}

private fun Double.format(digits: Int): CharSequence {
    return "%.${digits}f".format(this)
}
