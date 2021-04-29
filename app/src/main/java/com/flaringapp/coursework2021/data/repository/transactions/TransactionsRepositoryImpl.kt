package com.flaringapp.coursework2021.data.repository.transactions

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.repository.transactions.models.AddTransaction
import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction
import com.flaringapp.coursework2021.data.repository.transactions.storage.TransactionsStorage
import kotlinx.coroutines.flow.MutableSharedFlow

class TransactionsRepositoryImpl: TransactionsRepository, TransactionsStorage {

    override val addTransactionFlow = MutableSharedFlow<Transaction>()

    override suspend fun getTransactions(managerId: String): CallResultList<Transaction> {
        return CallResult.Success(emptyList())
    }

    override suspend fun makeTransaction(transaction: AddTransaction): CallResult<Transaction> {
        TODO("Not yet implemented")
    }
}