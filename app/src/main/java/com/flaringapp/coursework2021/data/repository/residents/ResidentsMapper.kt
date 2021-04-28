package com.flaringapp.coursework2021.data.repository.residents

import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse
import com.flaringapp.coursework2021.data.repository.residents.models.Resident

fun Resident.asRequest() = ResidentRequest(
    id,
    name,
    surname,
    email,
    description,
    buildingId,
)

fun ResidentResponse.parseResident() = Resident(
    id,
    name,
    surname,
    email,
    description ?: "",
    buildingId,
    buildingName,
)