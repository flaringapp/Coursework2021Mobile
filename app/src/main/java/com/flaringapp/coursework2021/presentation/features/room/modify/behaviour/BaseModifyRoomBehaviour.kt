package com.flaringapp.coursework2021.presentation.features.room.modify.behaviour

import com.flaringapp.coursework2021.data.repository.entity.EntityRepository
import com.flaringapp.coursework2021.data.repository.entity.models.Room
import com.flaringapp.coursework2021.presentation.features.room.modify.models.RoomEditableData
import kotlinx.parcelize.IgnoredOnParcel
import org.koin.core.context.GlobalContext

abstract class BaseModifyRoomBehaviour: ModifyRoomBehaviour {

    @IgnoredOnParcel
    override val room: Room? = null

    @IgnoredOnParcel
    protected val repository: EntityRepository by GlobalContext.get().inject()

    @IgnoredOnParcel
    override val preliminaryData: RoomEditableData? = null

}