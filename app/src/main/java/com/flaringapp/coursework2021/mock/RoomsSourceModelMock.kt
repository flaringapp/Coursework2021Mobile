package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.replaceFirstOrAdd
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.rooms.RoomsSourceModel
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse

class RoomsSourceModelMock : RoomsSourceModel {

    private val rooms: MutableList<RoomResponse> = mutableListOf(
        RoomResponse("1", "1", "R1", "Description room 1", "open_space", 1, 0, 12, 2),
        RoomResponse("2", "1", "R2", "Description room 2", "private", 1, 1, 4, 1),
    )

    override suspend fun getRooms(): CallResultList<RoomResponse> {
        return CallResult.Success(rooms)
    }

    override suspend fun addRoom(room: RoomRequest): CallResult<RoomResponse> {
        val response = room.toResponse()
        rooms += response
        return CallResult.Success(response)
    }

    override suspend fun editRoom(room: RoomRequest): CallResult<RoomResponse> {
        val response = room.toResponse()
        rooms.replaceFirstOrAdd(response) { it.id == response.id }
        return CallResult.Success(response)
    }

    override suspend fun deleteRoom(id: String): CallResultNothing {
        rooms.removeFirst { it.id == id }
        return CallResult.Nothing
    }

    private fun RoomRequest.toResponse() = RoomResponse(
        id, "1", name, description, type, hasBoard, hasBalcony, workplacesCount, windowCount, area
    )
}