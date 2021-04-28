package com.flaringapp.coursework2021.presentation.features.room.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateRoomBehaviour: BaseModifyRoomBehaviour() {

    @IgnoredOnParcel
    override val room: Room? = null

    override suspend fun modifyRoom(room: Room): CallResultNothing {
        return repository.addRoom(room)
            .ignoreData()
    }
}