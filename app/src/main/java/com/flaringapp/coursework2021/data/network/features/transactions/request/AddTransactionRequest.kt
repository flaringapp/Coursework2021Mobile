package com.flaringapp.coursework2021.data.network.features.transactions.request

import com.squareup.moshi.Json

class AddTransactionRequest(
    @Json(name = "rent_id")
    val rentId: String,
    @Json(name = "manager_id")
    val managerId: String,
    val amount: Int
)