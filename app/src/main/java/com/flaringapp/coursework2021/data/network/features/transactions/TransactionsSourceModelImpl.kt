package com.flaringapp.coursework2021.data.network.features.transactions

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.features.transactions.request.AddTransactionRequest
import com.flaringapp.coursework2021.data.network.features.transactions.response.TransactionResponse

class TransactionsSourceModelImpl(
    private val api: TransactionsApiService
): TransactionsSourceModel {

    override suspend fun getTransactions(managerId: String): CallResultList<TransactionResponse> {
        return api.getTransactions(managerId)
            .validateList()
    }

    override suspend fun addTransaction(transaction: AddTransactionRequest): CallResult<TransactionResponse> {
        return api.addTransaction(transaction)
            .validate()
    }
}