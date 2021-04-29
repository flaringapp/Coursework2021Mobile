package com.flaringapp.coursework2021.data.network.features.transactions.response

import com.squareup.moshi.Json
import java.time.LocalDateTime

class TransactionResponse(
    val id: String,
    @Json(name = "rent_id")
    val rentId: String,
    @Json(name = "resident_id")
    val residentId: String,
    @Json(name = "resident_name")
    val residentName: String,
    @Json(name = "resident_surname")
    val residentSurname: String,
    @Json(name = "manager_id")
    val managerId: String,
    val amount: Int,
    @Json(name="time_created")
    val timeCreated: LocalDateTime
)
