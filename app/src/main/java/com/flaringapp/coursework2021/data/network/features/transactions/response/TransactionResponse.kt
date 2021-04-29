package com.flaringapp.coursework2021.data.network.features.transactions.response

import com.squareup.moshi.Json
import java.time.LocalDate
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
    @Json(name = "room_id")
    val roomId: String,
    @Json(name = "room_name")
    val roomName: String,
    @Json(name = "room_type")
    val roomType: String,
    @Json(name = "manager_id")
    val managerId: String,
    @Json(name = "manager_id")
    val manager: String,
    @Json(name = "date_paid_from")
    val dateFrom: LocalDate,
    @Json(name = "date_paid_to")
    val dateTo: LocalDate,
    val amount: Int,
    @Json(name="time_created")
    val timeCreated: LocalDateTime
)
