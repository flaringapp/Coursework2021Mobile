package com.flaringapp.coursework2021.presentation.features.transaction.create.model

import androidx.lifecycle.LiveData
import com.flaringapp.coursework2021.presentation.base.BaseViewModel
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.TransactionSelectRentalViewData
import com.flaringapp.coursework2021.presentation.features.transaction.create.models.TransactionSelectResidentViewData

abstract class CreateTransactionModel: BaseViewModel() {

    abstract val residentsData: LiveData<TransactionSelectResidentViewData>
    abstract val rentalsData: LiveData<TransactionSelectRentalViewData>
    abstract val monthsCountData: LiveData<Int>

    abstract val residentErrorData: LiveData<Int?>
    abstract val rentalErrorData: LiveData<Int?>

    abstract val residentsAvailableData: LiveData<Boolean>
    abstract val rentalsAvailableData: LiveData<Boolean>

    abstract val priceData: LiveData<CharSequence>

    abstract val loadingData: LiveData<Boolean>

    abstract val closeScreenData: LiveData<Unit>

    abstract fun handleResidentChanged(id: String)
    abstract fun handleRentalChanged(id: String)
    abstract fun handleMonthCountChanged(monthsCount: Int)

    abstract fun createTransaction()

}