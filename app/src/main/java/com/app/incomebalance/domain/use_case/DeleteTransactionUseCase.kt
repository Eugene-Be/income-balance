package com.app.incomebalance.domain.use_case


import com.app.incomebalance.common.Resource
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DeleteTransactionUseCase @Inject constructor(val repository: TransactionRepository) {

    fun execute(transaction: Transaction): Flow<Resource<List<Transaction>>> = flow {
        emit(Resource.Loading())

        try {
            repository.delete(transaction.id!!)
            emit(Resource.Success<List<Transaction>>(null))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error<List<Transaction>>("Something is wrong"))
        }
    }
}