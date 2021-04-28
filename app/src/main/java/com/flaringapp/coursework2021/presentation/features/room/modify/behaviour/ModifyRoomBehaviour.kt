package com.flaringapp.coursework2021.presentation.features.room.modify.behaviour

import android.os.Parcelable
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.presentation.features.room.modify.models.RoomEditableData

interface ModifyRoomBehaviour: Parcelable {

    val room: Room?
    val preliminaryData: RoomEditableData?

    suspend fun modifyRoom(room: Room): CallResultNothing

}