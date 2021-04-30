package com.flaringapp.coursework2021.data.network.features.rentals.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RentalRequest(
    @Json(name = "room_id")
    val roomId: String,
    @Json(name = "user_id")
    val residentId: String,
)