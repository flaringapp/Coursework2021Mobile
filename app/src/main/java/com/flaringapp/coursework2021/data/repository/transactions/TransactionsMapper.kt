package com.flaringapp.coursework2021.data.repository.transactions

import com.flaringapp.coursework2021.app.common.getKey
import com.flaringapp.coursework2021.data.network.features.transactions.request.AddTransactionRequest
import com.flaringapp.coursework2021.data.network.features.transactions.response.TransactionResponse
import com.flaringapp.coursework2021.data.repository.entity.roomTypeKeys
import com.flaringapp.coursework2021.data.repository.transactions.models.AddTransaction
import com.flaringapp.coursework2021.data.repository.transactions.models.Transaction
import com.flaringapp.coursework2021.data.repository.transactions.models.TransactionResident
import com.flaringapp.coursework2021.data.repository.transactions.models.TransactionRoom

fun TransactionResponse.parseTransaction() = Transaction(
    id,
    rentalId,
    TransactionResident(residentId, residentName, residentSurname),
    TransactionRoom(roomId, roomName, roomTypeKeys.getKey(roomType)!!, roomPrice),
    managerId,
    dateFrom,
    dateTo,
    amount,
    timeCreated
)

fun AddTransaction.asRequest() = AddTransactionRequest(
    rentalId, managerId, monthCount
)