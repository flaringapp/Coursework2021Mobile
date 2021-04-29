package com.flaringapp.coursework2021.presentation.features.transaction.create.models

import com.flaringapp.coursework2021.data.repository.transactions.models.AddTransaction

class TransactionEditableData(
    var residentId: String? = null,
    var residentName: String? = null,
    var rentalId: String? = null,
    var rentalName: String? = null,
    var monthsCount: Int = DEFAULT_MONTHS_COUNT
) {
    companion object {
        private const val DEFAULT_MONTHS_COUNT = 1
    }

    fun toAddTransaction(managerId: String) = AddTransaction(
        managerId,
        rentalId!!,
        monthsCount
    )
}