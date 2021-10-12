package com.app.incomebalance.presentation.transactions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.TransactionItemBinding

class TransactionListAdapter(
    private val transactionClickListener: TransactionClickListener
) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionHolder>() {

    var transactions: List<Transaction> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val binding =
            TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        setClickListeners(holder, position)
        holder.applyData(transactions[position])
    }

    private fun setClickListeners(
        holder: TransactionHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            transactionClickListener.transactionClicked(
                transactions[position]
            )
        }
        holder.itemView.setOnLongClickListener {
            transactionClickListener.transactionLongPressed(
                transactions[position]
            )
        }
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    interface TransactionClickListener {
        fun transactionLongPressed(transaction: Transaction): Boolean
        fun transactionClicked(transaction: Transaction)
    }

    inner class TransactionHolder(binding: TransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val transactionName = binding.transactionName
        private val transactionValue = binding.transactionValue

        fun applyData(transaction: Transaction) {
            transactionName.text = transaction.name
            transactionValue.text = transaction.value.toString()
        }
    }
}

