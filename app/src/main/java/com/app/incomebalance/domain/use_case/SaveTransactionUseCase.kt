package com.app.incomebalance.domain.use_case

import android.content.Context
import com.app.incomebalance.R
import com.app.incomebalance.common.Resource
import com.app.incomebalance.data.Transaction
import com.app.incomebalance.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository,
    private val context: Context
) {

    fun execute(transaction: Transaction): Flow<Resource<Any?>> = flow {
        emit(Resource.Loading())

        when (true) {
            transaction.fieldsIsEmpty() -> emit(Resource.Error<Any?>(context.getString(R.string.empty_fields)))
            transaction.nameIsEmpty() -> emit(Resource.Error<Any?>(context.getString(R.string.no_title)))
            transaction.valueIsZero() -> emit(Resource.Error<Any?>(context.getString(R.string.no_value)))
            else ->
                try {
                    repository.save(transaction)
                    emit(Resource.Success<Any?>(null))
                } catch (e: Exception) {
                    emit(Resource.Error<Any?>("db error"))
                }
        }
    }
}

private fun Transaction.valueIsZero(): Boolean {
    return this.value == 0.0
}

private fun Transaction.fieldsIsEmpty(): Boolean {
    return this.name?.isEmpty() == true && this.value == 0.0
}

private fun Transaction.nameIsEmpty(): Boolean {
    return this.name?.isEmpty() == true
}