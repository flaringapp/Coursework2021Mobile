package com.flaringapp.coursework2021.data.network.features.profile.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginResponse(
    val id: String,
    @Json(name = "first_name")
    val name: String,
    @Json(name = "last_name")
    val surname: String,
    val email: String,
    @Json(name = "token")
    val token: String,
    @Json(name = "user_type")
    val userType: String,
    @Json(name = "location_id")
    val buildingId: String? = null,
)