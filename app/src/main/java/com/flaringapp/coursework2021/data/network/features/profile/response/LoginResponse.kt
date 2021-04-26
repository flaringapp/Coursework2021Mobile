package com.flaringapp.coursework2021.data.network.features.profile.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginResponse(
    @Json(name = "uuid")
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    @Json(name = "apiKey")
    val token: String,
)