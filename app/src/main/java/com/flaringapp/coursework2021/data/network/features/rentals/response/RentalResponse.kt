package com.flaringapp.coursework2021.data.network.features.rentals.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RentalResponse(
    val id: String,
    @Json(name = "room_id")
    val roomId: String,
    @Json(name = "room_name")
    val roomName: String,
    @Json(name = "room_price")
    val roomPrice: Int,
    @Json(name = "resident_id")
    val residentId: String,
    @Json(name = "resident_name")
    val residentName: String,
    @Json(name = "resident_surname")
    val residentSurname: String,
)