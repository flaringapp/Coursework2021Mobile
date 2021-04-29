package com.flaringapp.coursework2021.presentation.features.transaction.create.models

class TransactionSelectRentalViewData(
    val rentals: List<TransactionRentalViewData> = emptyList(),
    val selectedRentalName: String? = null
)