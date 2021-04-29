package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.network.features.rentals.request.RentalRequest
import com.flaringapp.coursework2021.data.network.features.rentals.response.RentalResponse
import com.flaringapp.coursework2021.data.repository.tenants.models.AddRental
import com.flaringapp.coursework2021.data.repository.tenants.models.Rental
import com.flaringapp.coursework2021.data.repository.tenants.models.RentalRoom
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant

fun AddRental.asRequest() = RentalRequest(
    roomId,
    residentId
)

fun RentalResponse.parseRent() = Rental(
    id,
    RentalRoom(
        roomId,
        roomName,
        roomPrice,
    ),
    Tenant(
        residentId,
        residentName,
        residentSurname
    )
)