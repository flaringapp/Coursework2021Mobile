package com.flaringapp.coursework2021.data.repository.transactions

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.repository.transactions.models.AddTransaction
import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction

interface TransactionsRepository {

    suspend fun getTransactions(managerId: String): CallResultList<Transaction>

    suspend fun makeTransaction(transaction: AddTransaction): CallResult<Transaction>

}