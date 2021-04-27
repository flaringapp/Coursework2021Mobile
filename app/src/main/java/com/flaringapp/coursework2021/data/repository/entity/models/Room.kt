package com.flaringapp.coursework2021.data.repository.entity.models

interface Room {

    val id: String
    val floorId: String

    val name: String
    val description: String

    val type: RoomType

    val area: Float?

    val windowCount: Int?
    val hasBalcony: Boolean?
    val hasBoard: Boolean?

    val placesCount: Int

}