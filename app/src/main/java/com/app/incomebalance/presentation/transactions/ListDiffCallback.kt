package com.app.incomebalance.presentation.transactions

import androidx.recyclerview.widget.DiffUtil
import com.app.incomebalance.data.Transaction

class ListDiffCallback(private val oldList: List<Transaction>,private val newList: List<Transaction>) : DiffUtil.Callback(){
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}