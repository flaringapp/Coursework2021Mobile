package com.flaringapp.coursework2021.data.network.features.transactions.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AddTransactionRequest(
    @Json(name = "rental_id")
    val rentalId: String,
    @Json(name = "manager_id")
    val managerId: String,
    @Json(name = "months_count")
    val monthsCount: Int
)