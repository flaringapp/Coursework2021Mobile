package com.flaringapp.coursework2021.data.network.features.rents.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RentRequest(
    val id: String,
    @Json(name = "room_id")
    val roomId: String,
    @Json(name = "resident_id")
    val residentId: String,
)