package com.flaringapp.coursework2021.data.repository.entity.storage

import com.flaringapp.coursework2021.data.repository.entity.models.Room
import kotlinx.coroutines.flow.Flow

interface RoomsStorage {

    val addRoomFlow: Flow<Room>
    val editRoomFlow: Flow<Room>
    val deleteRoomFlow: Flow<Room>

}