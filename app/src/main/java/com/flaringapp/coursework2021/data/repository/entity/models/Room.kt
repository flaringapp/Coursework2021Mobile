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

    val hasBoard: Boolean?,
    val hasBalcony: Boolean?,

    val placesCount: Int?,
    val windowCount: Int?,

    val area: Float?,
): Parcelable {

    companion object {
        const val NO_ID = "0"
    }

}