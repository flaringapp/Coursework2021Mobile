package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.network.features.rents.request.RentRequest
import com.flaringapp.coursework2021.data.network.features.rents.response.RentResponse
import com.flaringapp.coursework2021.data.repository.tenants.models.Rent
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant

fun Rent.asRequest() = RentRequest(
    id,
    roomId,
    tenant.residentId
)

fun RentResponse.parseRent() = Rent(
    id,
    roomId,
    Tenant(
        residentId,
        residentName,
        residentSurname
    )
)