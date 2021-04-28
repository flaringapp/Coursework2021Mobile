package com.flaringapp.coursework2021.data.network.features.managers.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ManagerResponse(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val description: String,
    @Json(name = "building_id")
    val buildingId: String,
    @Json(name = "building_name")
    val buildingName: String,
)