package com.app.incomebalance.data

import androidx.room.*
import com.app.incomebalance.domain.TransactionType

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE transactionType = :transactionType")
    suspend fun getTransactionsByType(transactionType: TransactionType): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransaction(id: Long): Transaction

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    @Query("DELETE FROM transactions WHERE id = :transactionId")
    suspend fun deleteTransaction(transactionId: Long)
}
