package com.app.incomebalance.data

import com.app.incomebalance.domain.TransactionType

data class Transaction(
    val id: Long,
    val name: String,
    val value: Double,
    val transactionType: TransactionType,
)