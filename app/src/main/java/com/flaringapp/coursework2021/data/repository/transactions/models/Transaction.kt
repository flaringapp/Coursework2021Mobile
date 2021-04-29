package com.flaringapp.coursework2021.data.repository.transactions.models

import java.time.LocalDate
import java.time.LocalDateTime

class Transaction(
    val id: String,
    val rentId: String,
    val resident: TransactionResident,
    val room: TransactionRoom,
    val managerId: String,
    val dateFrom: LocalDate,
    val dateTo: LocalDate,
    val amount: Int,
    val timeCreated: LocalDateTime
)