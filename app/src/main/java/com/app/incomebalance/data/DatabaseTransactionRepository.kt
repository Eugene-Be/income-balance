package com.app.incomebalance.data

import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.domain.model.Balance
import com.app.incomebalance.domain.repository.TransactionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseTransactionRepository @Inject constructor(private val database: TransactionDatabase) : TransactionRepository {

    override suspend fun getIncomes(): List<Transaction> {
        return database.dao.getTransactionsByType(TransactionType.INCOME) //?: listOf()
    }

    override suspend fun getOutcomes(): List<Transaction> {
        return database.dao.getTransactionsByType(TransactionType.OUTCOME) //?: listOf()
    }

    override suspend fun getBalance(): Balance {
        val incomes = getIncomes().sumOf { it.value }
        val outcomes = getOutcomes().sumOf { it.value }
        val balance = incomes - outcomes
        return Balance(balance, incomes, outcomes)
    }

    override suspend fun save(transaction: Transaction) {
        database.dao.insertTransaction(transaction)
    }

    override suspend fun delete(transactionId: Long) {
        database.dao.deleteTransaction(transactionId)
    }
}