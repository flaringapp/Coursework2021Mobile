package com.flaringapp.coursework2021.data.repository.transactions.models

import java.time.LocalDateTime

class Transaction(
    val id: String,
    val resident: TransactionResident,
    val managerId: String,
    val amount: Int,
    val timeCreated: LocalDateTime
)