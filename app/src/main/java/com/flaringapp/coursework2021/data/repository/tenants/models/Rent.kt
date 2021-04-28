package com.flaringapp.coursework2021.data.repository.tenants.models

class Rent(
    val id: String,
    val roomId: String,
    val tenant: Tenant,
) {

    companion object {
        const val NO_ID = "0"
    }

}