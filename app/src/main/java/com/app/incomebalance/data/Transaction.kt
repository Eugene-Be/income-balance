package com.app.incomebalance.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.incomebalance.domain.TransactionType

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = -1,
    val name: String = "",
    val value: Double = 0.0,
    val transactionType: TransactionType,
)