package com.app.incomebalance.view.transactions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.databinding.TransactionItemBinding

class TransactionListAdapter(
    private val transactions: List<Transaction>,
    private val transactionClickListener: TransactionClickListener
) :
    RecyclerView.Adapter<TransactionListAdapter.TransactionHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        val binding =
            TransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        holder.applyData(transactions[position])
    }

    override fun getItemCount(): Int {
        return transactions.size
    }


    interface TransactionClickListener {
        fun longPressed(transaction: Transaction)
        fun clicked(transaction: Transaction)
    }


    inner class TransactionHolder(view: View) : RecyclerView.ViewHolder(view),
        View.OnLongClickListener, View.OnClickListener {
        var binding = TransactionItemBinding.bind(view)
        val itemPosition = this.layoutPosition
        fun applyData(transaction: Transaction) {
            binding.transactionName.text = transaction.name
            binding.transactionValue.text = transaction.value.toString()
        }

        override fun onLongClick(v: View?): Boolean {

            transactionClickListener.longPressed(transactions[itemPosition])
            return true
        }

        override fun onClick(v: View?) {
            transactionClickListener.clicked(transactions[itemPosition])
        }
    }
}
