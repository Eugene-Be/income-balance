package com.app.incomebalance.presentation.transactions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.incomebalance.common.Constants
import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.common.appComponent
import com.app.incomebalance.contracts.TransactionListContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.FragmentTransactionListBinding
import com.app.incomebalance.presentation.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionListFragment :
    BaseFragment<FragmentTransactionListBinding, TransactionListContract.Presenter>(),
    TransactionListContract.View,
    TransactionListAdapter.TransactionClickListener {

    private var transactionAdapter: TransactionListAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.appComponent.inject(this)
        setPresenterType()
    }

    private fun setPresenterType() {
        arguments?.let {
            val transactionType = TransactionType.values()[it.getInt(Constants.TYPE_NUMBER_KEY)]
            presenter.setType(transactionType)
            Log.i("setPresenterType", "setPresenterType: $transactionType")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFloatingButton()
        setupTransactionList()
    }

    private fun setupFloatingButton() {
        binding.fab.setOnClickListener {
            presenter.onAddTransactionPressed()
        }
    }

    override fun showProgress() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun setupTransactionList() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        transactionAdapter = TransactionListAdapter(this)
        binding.recyclerView.adapter = transactionAdapter
    }

    override fun updateTransactionList(newList: List<Transaction>) {
        binding.progressCircular.visibility = View.INVISIBLE
        val oldList = transactionAdapter?.transactions ?: listOf()
        val callback = ListDiffCallback(oldList, newList)
        transactionAdapter?.transactions = newList

        CoroutineScope(Dispatchers.Default).launch {
            val result = DiffUtil.calculateDiff(callback)
            withContext(Dispatchers.Main) {
                transactionAdapter?.let { result.dispatchUpdatesTo(it) }
            }
        }
    }

    private fun showDialog(transaction: Transaction) {
        val transactionString = transaction.name + transaction.value
        val dialog = DeleteConfirmationDialog.newInstance(transactionString)
        setResultListener(transaction)

        dialog.show(childFragmentManager, "start dialog")
    }

    private fun setResultListener(transaction: Transaction) {
        childFragmentManager.setFragmentResultListener(
            Constants.REQUEST_KEY,
            this,
            { _, _ -> presenter.deleteTransaction(transaction) })
    }


    override fun transactionLongPressed(transaction: Transaction): Boolean {
        showDialog(transaction)
        return true
    }

    override fun transactionClicked(transaction: Transaction) {
        presenter.onTransactionClicked(transaction)
    }

    override fun getBinding(container: ViewGroup?): FragmentTransactionListBinding {
        return FragmentTransactionListBinding.inflate(layoutInflater, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            TransactionListFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.TYPE_NUMBER_KEY, param1)
                }
            }
    }
}