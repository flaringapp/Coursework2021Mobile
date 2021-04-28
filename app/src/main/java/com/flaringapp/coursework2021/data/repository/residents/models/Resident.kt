package com.flaringapp.coursework2021.data.repository.residents.models

class Resident(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val description: String,
    val buildingId: String?,
    val buildingName: String?,
)