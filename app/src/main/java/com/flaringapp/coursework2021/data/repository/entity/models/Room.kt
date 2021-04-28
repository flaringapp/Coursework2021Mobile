package com.flaringapp.coursework2021.data.repository.entity.models

class Room(
    val id: String,
    val buildingId: String,

    val name: String,
    val description: String,

    val type: RoomType,

    val area: Float?,

    val hasBalcony: Boolean?,
    val hasBoard: Boolean?,

    val windowCount: Int?,
    val placesCount: Int?
)