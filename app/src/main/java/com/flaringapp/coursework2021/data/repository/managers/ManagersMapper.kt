package com.flaringapp.coursework2021.data.repository.managers

import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse
import com.flaringapp.coursework2021.data.repository.managers.models.Manager

fun Manager.asRequest() = ManagerRequest(
    id,
    name,
    surname,
    email,
    description,
    buildingId,
)

fun ManagerResponse.parseManager() = Manager(
    id,
    name,
    surname,
    email,
    description,
    buildingId,
    buildingName
)