package com.flaringapp.coursework2021.data.network.features.managers.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ManagerResponse(
    val id: String,
    @Json(name = "first_name")
    val name: String,
    @Json(name = "last_name")
    val surname: String,
    val email: String,
    val description: String? = null,
    @Json(name = "location_id")
    val buildingId: String,
    @Json(name = "location_name")
    val buildingName: String,
)