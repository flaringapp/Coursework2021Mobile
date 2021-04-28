package com.flaringapp.coursework2021.data.repository.entity.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Room(
    val id: String,
    val buildingId: String,

    val name: String,
    val description: String,

    val type: RoomType,

    val area: Float?,

    val hasBoard: Boolean?,
    val hasBalcony: Boolean?,

    val placesCount: Int?,
    val windowCount: Int?,
): Parcelable {

    companion object {
        const val NO_ID = "0"
    }

}