package com.flaringapp.coursework2021.data.network.features.buildings.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BuildingsResponse(
    val id: String,
    val name: String,
    val description: String? = null,
    val lat: Double? = null,
    val lon: Double? = null,
    val address: String? = null,
    val area: Float? = null,
)