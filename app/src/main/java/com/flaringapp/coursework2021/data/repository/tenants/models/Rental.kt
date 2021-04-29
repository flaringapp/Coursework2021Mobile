package com.flaringapp.coursework2021.data.repository.tenants.models

class Rental(
    val id: String,
    val room: RentalRoom,
    val tenant: Tenant,
) {

    companion object {
        const val NO_ID = "0"
    }

}