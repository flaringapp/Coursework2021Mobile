package com.flaringapp.coursework2021.data.repository.transactions

import com.flaringapp.coursework2021.data.network.features.transactions.request.AddTransactionRequest
import com.flaringapp.coursework2021.data.network.features.transactions.response.TransactionResponse
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType
import com.flaringapp.coursework2021.data.repository.transactions.models.AddTransaction
import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction
import com.flaringapp.coursework2021.data.repository.transactions.models.TransactionResident
import com.flaringapp.coursework2021.data.repository.transactions.models.TransactionRoom

fun TransactionResponse.parseTransaction() = Transaction(
    id,
    rentalId,
    parseResident(),
    parseRoom(),
    managerId,
    dateFrom,
    dateTo,
    amount,
    timeCreated
)

fun TransactionResponse.parseResident(): TransactionResident? {
    return TransactionResident(
        residentId ?: return null,
        residentName ?: "No",
        residentSurname ?: "name"
    )
}

fun TransactionResponse.parseRoom(): TransactionRoom? {
    return TransactionRoom(
        roomId ?: return null,
        roomName ?: "No name",
        RoomType.Unknown
    )
}

fun AddTransaction.asRequest() = AddTransactionRequest(
    rentalId, managerId, monthCount
)