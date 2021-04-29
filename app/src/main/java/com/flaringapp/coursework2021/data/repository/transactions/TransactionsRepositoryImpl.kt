package com.flaringapp.coursework2021.data.repository.transactions

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.transformList
import com.flaringapp.coursework2021.data.network.features.transactions.TransactionsSourceModel
import com.flaringapp.coursework2021.data.repository.transactions.models.AddTransaction
import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction
import com.flaringapp.coursework2021.data.repository.transactions.storage.TransactionsStorage
import kotlinx.coroutines.flow.MutableSharedFlow

class TransactionsRepositoryImpl(
    private val sourceModel: TransactionsSourceModel
): TransactionsRepository, TransactionsStorage {

    override val addTransactionFlow = MutableSharedFlow<Transaction>()

    override suspend fun getTransactions(managerId: String): CallResultList<Transaction> {
        return sourceModel.getTransactions(managerId)
            .transformList { parseTransaction() }
    }

    override suspend fun makeTransaction(transaction: AddTransaction): CallResult<Transaction> {
        return sourceModel.addTransaction(transaction.asRequest())
            .transform { parseTransaction() }
            .doOnSuccessSuspend { addTransactionFlow.emit(it) }
    }
}