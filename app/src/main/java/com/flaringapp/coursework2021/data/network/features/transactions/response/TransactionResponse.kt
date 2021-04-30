package com.flaringapp.coursework2021.data.network.features.transactions.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate
import java.time.LocalDateTime

@JsonClass(generateAdapter = true)
class TransactionResponse(
    val id: String,
    @Json(name = "rental_id")
    val rentalId: String,
    @Json(name = "user_id")
    val residentId: String,
    @Json(name = "user_first_name")
    val residentName: String,
    @Json(name = "user_last_name")
    val residentSurname: String,
    @Json(name = "room_id")
    val roomId: String,
    @Json(name = "room_name")
    val roomName: String,
    @Json(name = "room_type")
    val roomType: String,
    @Json(name = "manager_id")
    val managerId: String,
    @Json(name = "date_from")
    val dateFrom: LocalDate,
    @Json(name = "date_to")
    val dateTo: LocalDate,
    val amount: Int,
    @Json(name = "time_created")
    val timeCreated: LocalDateTime
)
