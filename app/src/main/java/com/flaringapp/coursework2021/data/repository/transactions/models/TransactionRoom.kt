package com.flaringapp.coursework2021.data.repository.transactions.models

import com.flaringapp.coursework2021.data.repository.entity.models.RoomType

class TransactionRoom(
    val roomId: String,
    val roomName: String,
    val roomType: RoomType,
    val roomPrice: Int,
)