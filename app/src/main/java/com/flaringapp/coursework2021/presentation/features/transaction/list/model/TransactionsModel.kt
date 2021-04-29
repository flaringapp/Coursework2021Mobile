package com.flaringapp.coursework2021.presentation.features.transaction.list.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.transaction.list.models.TransactionViewData

abstract class TransactionsModel: BaseViewModel() {

    abstract val transactionsData: LiveData<List<TransactionViewData>>
    abstract val addTransactionData: LiveData<TransactionViewData>

    abstract val openNewTransactionData: LiveData<Unit>

    abstract fun addNewTransaction()

}