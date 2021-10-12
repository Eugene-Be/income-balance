package com.app.incomebalance.domain.use_case

import android.util.Log
import com.app.incomebalance.common.Resource
import com.app.incomebalance.common.TransactionType
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetTransactionsUseCase @Inject constructor(val repository: TransactionRepository) {

    fun execute(type: TransactionType): Flow<Resource<List<Transaction>>> = flow {
        emit(Resource.Loading<List<Transaction>>())

        try {
            Log.i("GetTransactionsUseCase", "execute: $type")
            val list: List<Transaction> = when (type) {
                TransactionType.INCOME -> repository.getIncomes()
                TransactionType.OUTCOME -> repository.getOutcomes()
                else -> listOf()
            }
            emit(Resource.Success(list))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error<List<Transaction>>("Something is wrong"))
        }
    }
}