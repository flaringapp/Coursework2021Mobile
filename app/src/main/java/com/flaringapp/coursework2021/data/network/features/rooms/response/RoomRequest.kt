package com.flaringapp.coursework2021.data.network.features.rooms.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class RoomRequest(
    val id: String,
    val name: String,
    val description: String? = null,
    val type: String,
    @Json(name = "has_board")
    val hasBoard: Int? = null,
    @Json(name = "has_balcony")
    val hasBalcony: Int? = null,
    @Json(name = "places_count")
    val workplacesCount: Int? = null,
    @Json(name = "window_count")
    val windowCount: Int? = null,
    val area: Float? = null,
)