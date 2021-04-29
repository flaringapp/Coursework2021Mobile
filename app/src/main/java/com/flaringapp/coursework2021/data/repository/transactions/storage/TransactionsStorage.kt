package com.flaringapp.coursework2021.data.repository.transactions.storage

import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionsStorage {

    val addTransactionFlow: Flow<Transaction>

}