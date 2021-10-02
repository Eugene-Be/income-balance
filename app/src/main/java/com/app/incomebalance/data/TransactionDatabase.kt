package com.app.incomebalance.data

import android.content.Context
import androidx.room.*

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {
    abstract val dao: TransactionDao

    companion object {
        var instance: TransactionDatabase? = null
        fun getInstance(context: Context?): TransactionDatabase? {

            if (instance == null) {
                instance = Room.databaseBuilder(context!!, TransactionDatabase::class.java, "TransactionsDb")
                    .allowMainThreadQueries().build()//todo main thread clear
            }
            return instance
        }
    }
}

