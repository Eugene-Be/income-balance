package com.app.incomebalance.data

import com.app.incomebalance.domain.Balance
import com.app.incomebalance.domain.TransactionType

class DatabaseTransactionRepository : TransactionRepository {
    private val database: TransactionDatabase = TransactionDatabase.getInstance(context = null)!!

    override suspend fun getIncomes(): List<Transaction> {
        return database.dao.getTransactionsByType(TransactionType.INCOME) ?: listOf()
    }


    override suspend fun getOutcomes(): List<Transaction> {
        return database.dao.getTransactionsByType(TransactionType.OUTCOME) ?: listOf()
    }

    override suspend fun getBalance(): Balance {
        val incomes = getIncomes().sumOf { it.value }
        val outcomes = getOutcomes().sumOf { it.value }
        val balance = incomes - outcomes
        return Balance(balance, incomes, outcomes)
    }

    override suspend fun create(transaction: Transaction): Transaction {
        val id = database.dao.insertTransaction(transaction)
        return database.dao.getTransaction(id)
    }

    override suspend fun update(transaction: Transaction) {
        database.dao.insertTransaction(transaction)
    }

    override suspend fun delete(transaction: Transaction) {
        database.dao.deleteTransaction(transaction)
    }
}