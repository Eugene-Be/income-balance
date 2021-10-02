package com.app.incomebalance.view.balance

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.incomebalance.R
import com.app.incomebalance.appComponent
import com.app.incomebalance.contracts.BalanceContract
import com.app.incomebalance.data.TransactionRepository
import com.app.incomebalance.databinding.FragmentBalanceBinding
import com.app.incomebalance.di.AppComponent
import com.app.incomebalance.view.BaseFragment
import com.app.incomebalance.view.transactions.TransactionListFragment
import com.app.incomebalance.view.transactions.TransactionListPresenter
import javax.inject.Inject

class BalanceFragment : BaseFragment() {
    private var _binding: FragmentBalanceBinding? = null
    private val binding get() = _binding!!

    val TAG = "BalanceFragmentTag"

    @Inject
    lateinit var presenter: BalancePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Log.i(TAG, "onCreateView: ")

        _binding = FragmentBalanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent.inject(this)
    }


    override fun onResume() {
        super.onResume()

        presenter.updateBalance()
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            BalanceFragment()
    }
}
