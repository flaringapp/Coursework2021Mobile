package com.flaringapp.coursework2021.data.repository.entity.models

class Building(
    val id: String,
    val name: String,
    val description: String,
    val location: GeoLocation? = null,
    val address: String? = null,
    val area: Float? = null,
)