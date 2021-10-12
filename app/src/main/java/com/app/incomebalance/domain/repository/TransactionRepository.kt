package com.app.incomebalance.domain.repository

import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.model.Balance

interface TransactionRepository {
    suspend fun getIncomes(): List<Transaction>
    suspend fun getOutcomes(): List<Transaction>
    suspend fun getBalance(): Balance
    suspend fun save(transaction: Transaction)
    suspend fun delete(transactionId: Long)
}