package com.app.incomebalance.view.transactions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.incomebalance.appComponent
import com.app.incomebalance.contracts.TransactionListContract
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.FragmentTransactionListBinding
import com.app.incomebalance.domain.TransactionType
import com.app.incomebalance.view.BaseFragment
import javax.inject.Inject

private const val typeNumber = "typeNumber"

class TransactionListFragment :
    BaseFragment(),
    TransactionListContract.View,
    DeleteConfirmationDialog.ConfirmationDialogListener,
    TransactionListAdapter.TransactionClickListener {

    private var _binding: FragmentTransactionListBinding? = null
    private val binding get() = _binding!!

    private var transactionType: TransactionType? = null

    @Inject
    lateinit var presenter: TransactionListPresenter

    private val TAG = "TransactionFragmentTag"

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.i(TAG, "onCreateView: ${transactionType?.name}")

        arguments?.let {
            transactionType = TransactionType.values()[it.getInt(typeNumber)]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFloatingButton()
    }

    private fun setupFloatingButton() {
        binding.fab.setOnClickListener {
            Log.i(TAG, "setupFloatingButton: ${transactionType?.name}")
            transactionType?.let { presenter.addTransaction(it) }
        }
    }

    override fun showTransactionList(transactions: List<Transaction>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = TransactionListAdapter(transactions, this)
    }

    override fun getViewContext(): Context {
        return requireContext()
    }

    override fun getViewFragmentManager(): FragmentManager? {
        return parentFragment?.requireActivity()?.supportFragmentManager
        //return parentFragmentManager
    }

    private fun showDialog(transaction: Transaction) {
        val transactionString = transaction.name + transaction.value
        val dialog = DeleteConfirmationDialog.newInstance(transactionString)

        childFragmentManager.setFragmentResultListener(
            "CONFIRMATION",
            this,
            { _, _ -> confirmDelete(transaction.id) })
        dialog.show(childFragmentManager, "start dialog")
    }

    override fun confirmDelete(transactionId: Long) {
        presenter.deleteTransaction(transactionId)
    }

    override fun longPressed(transaction: Transaction) {
        showDialog(transaction)
    }

    override fun clicked(transaction: Transaction) {
        presenter.editTransaction(transaction)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            TransactionListFragment().apply {
                arguments = Bundle().apply {
                    putInt(typeNumber, param1)
                }
            }
    }
}