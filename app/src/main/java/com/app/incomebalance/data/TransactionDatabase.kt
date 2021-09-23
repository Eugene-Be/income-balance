package com.app.incomebalance.data

import android.content.Context
import androidx.room.*
import com.app.incomebalance.domain.TransactionType

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {
    abstract val dao: TransactionDao

    companion object {
        var instance: TransactionDatabase? = null
        fun getInstance(context: Context?): TransactionDatabase? {

            return instance ?: Room.databaseBuilder(
                context!!,
                TransactionDatabase::class.java,
                "TransactionDb"
            ).build()
        }
    }
}

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions WHERE transactionType = :transactionType")
    suspend fun getTransactionsByType(transactionType: TransactionType): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getTransaction(id: Long): Transaction

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: Transaction): Long

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}
