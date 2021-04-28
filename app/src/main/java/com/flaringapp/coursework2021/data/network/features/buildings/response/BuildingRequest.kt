package com.flaringapp.coursework2021.data.network.features.buildings.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BuildingRequest(
    val id: String,
    val name: String,
    val description: String?,
    val lat: Double?,
    val lon: Double?,
    val address: String?,
    val area: Float?,
)