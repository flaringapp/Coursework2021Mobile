package com.flaringapp.coursework2021.data.network.features.residents.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ResidentResponse(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val description: String? = null,
    @Json(name = "building_id")
    val buildingId: String,
    @Json(name = "building_name")
    val buildingName: String,
)