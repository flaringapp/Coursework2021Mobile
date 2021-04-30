package com.flaringapp.coursework2021.data.network.features.profile.request

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginRequest(
    val email: String,
    val password: String
)