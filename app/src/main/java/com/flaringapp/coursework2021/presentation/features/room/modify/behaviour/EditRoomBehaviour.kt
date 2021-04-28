package com.flaringapp.coursework2021.presentation.features.room.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.presentation.features.room.modify.models.RoomEditableData
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class EditRoomBehaviour(
    override val room: Room
): BaseModifyRoomBehaviour() {

    @IgnoredOnParcel
    override val preliminaryData: RoomEditableData
        get() = RoomEditableData(room)

    override suspend fun modifyRoom(room: Room): CallResultNothing {
        return repository.editRoom(room)
            .ignoreData()
    }
}