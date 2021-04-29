package com.flaringapp.coursework2021.data.network.features.transactions

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.network.features.transactions.request.AddTransactionRequest
import com.flaringapp.coursework2021.data.network.features.transactions.response.TransactionResponse

interface TransactionsSourceModel {

    suspend fun getTransactions(managerId: String): CallResultList<TransactionResponse>

    suspend fun addTransaction(transaction: AddTransactionRequest): CallResult<TransactionResponse>

}