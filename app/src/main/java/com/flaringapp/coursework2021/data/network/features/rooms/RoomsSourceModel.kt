package com.flaringapp.coursework2021.data.network.features.rooms

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse

interface RoomsSourceModel {

    suspend fun getRooms(): CallResultList<RoomResponse>
    suspend fun getRooms(buildingId: String): CallResultList<RoomResponse>

    suspend fun addRoom(room: RoomRequest): CallResult<RoomResponse>

    suspend fun editRoom(room: RoomRequest): CallResult<RoomResponse>

    suspend fun deleteRoom(id: String): CallResultNothing

}