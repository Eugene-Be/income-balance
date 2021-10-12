package com.app.incomebalance.domain.use_case

import com.app.incomebalance.common.Resource
import com.app.incomebalance.domain.model.Balance
import com.app.incomebalance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetBalanceUseCase @Inject constructor(val repository: TransactionRepository) {

    fun execute(): Flow<Resource<Balance>> = flow {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(repository.getBalance()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error<Balance>("Something is wrong"))
        }
    }
}