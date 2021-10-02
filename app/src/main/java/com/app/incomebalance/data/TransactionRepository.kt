package com.app.incomebalance.data

import com.app.incomebalance.domain.Balance

interface TransactionRepository {
    suspend fun getIncomes(): List<Transaction>
    suspend fun getOutcomes(): List<Transaction>
    suspend fun getBalance(): Balance
    suspend fun create(transaction: Transaction): Transaction
    suspend fun update(transaction: Transaction)
    suspend fun delete(transactionId: Long)
}