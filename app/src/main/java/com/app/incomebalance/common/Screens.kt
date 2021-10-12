@file:Suppress("FunctionName")

package com.app.incomebalance.common

import com.app.incomebalance.data.Transaction
import com.app.incomebalance.presentation.pages.PagerFragment
import com.app.incomebalance.presentation.transaction_edit.CreateTransactionFragment
import com.app.incomebalance.presentation.transaction_edit.EditTransactionFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun EditTransactionScreen(transaction: Transaction) = FragmentScreen {
        EditTransactionFragment.newInstance(transaction)
    }

    fun CreateTransactionScreen(transactionType: TransactionType) = FragmentScreen {
        CreateTransactionFragment.newInstance(transactionType.ordinal)
    }

    fun ViewPagerScreen() = FragmentScreen {
        PagerFragment.newInstance()
    }
}