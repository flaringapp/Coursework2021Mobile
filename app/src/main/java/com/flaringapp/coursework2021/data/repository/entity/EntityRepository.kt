package com.flaringapp.coursework2021.data.repository.entity

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.entity.models.Room

interface EntityRepository {

    suspend fun getBuildings(): CallResultList<Building>

    suspend fun addBuilding(building: Building): CallResult<Building>
    suspend fun editBuilding(building: Building): CallResult<Building>
    suspend fun deleteBuilding(id: String): CallResultNothing


    suspend fun getRooms(buildingId: String): CallResultList<Room>

    suspend fun addRoom(room: Room): CallResult<Room>
    suspend fun editRoom(room: Room): CallResult<Room>
    suspend fun deleteRoom(id: String): CallResultNothing

}