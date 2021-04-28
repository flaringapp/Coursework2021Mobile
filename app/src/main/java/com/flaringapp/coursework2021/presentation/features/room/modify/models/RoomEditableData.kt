package com.flaringapp.coursework2021.presentation.features.room.modify.models

import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.data.repository.entity.models.RoomType

class RoomEditableData(
    var name: String = "",
    var description: String = "",
    var type: RoomType = RoomType.Private,
    var hasBoard: Boolean = false,
    var hasBalcony: Boolean = false,
    var workplacesCount: Int = DEFAULT_PLACES_COUNT,
    var windowCount: Int = DEFAULT_WINDOW_COUNT,
    var area: String = "",
) {

    companion object {
        private const val DEFAULT_PLACES_COUNT = 4
        private const val DEFAULT_WINDOW_COUNT = 2
    }

    constructor(room: Room) : this(
        room.name,
        room.description,
        room.type,
        room.hasBoard == true,
        room.hasBalcony == true,
        room.workplacesCount ?: DEFAULT_PLACES_COUNT,
        room.windowCount ?: DEFAULT_WINDOW_COUNT,
        room.area?.toString() ?: "",
    )

    fun toRoom(room: Room?, buildingId: String) = Room(
        room?.id ?: Room.NO_ID,
        buildingId,
        name.trim(),
        description.trim(),
        type,
        hasBoard,
        hasBalcony,
        workplacesCount,
        windowCount,
        area.trim().toFloatOrNull(),
    )

}