package com.flaringapp.coursework2021.data.network.features.rooms

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.base.validateNoData
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse

class RoomsSourceModelImpl(
    private val api: RoomsApiService
): RoomsSourceModel {

    override suspend fun getRooms(): CallResultList<RoomResponse> {
        return api.getRooms()
            .validateList()
    }

    override suspend fun addRoom(room: RoomRequest): CallResult<RoomResponse> {
        return api.addRoom(room)
            .validate()
    }

    override suspend fun editRoom(room: RoomRequest): CallResult<RoomResponse> {
        return api.editRoom(room)
            .validate()
    }

    override suspend fun deleteRoom(id: String): CallResultNothing {
        return api.deleteRoom(id)
            .validateNoData()
    }
}