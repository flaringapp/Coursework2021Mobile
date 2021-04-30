package com.flaringapp.coursework2021.data.network.features.managers.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ManagerRequest(
    val id: String,
    @Json(name = "first_name")
    val name: String,
    @Json(name = "last_name")
    val surname: String,
    val email: String,
    val password: String?,
    val description: String,
    @Json(name = "location_id")
    val buildingId: String,
)