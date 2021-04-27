package com.flaringapp.coursework2021.data.repository.entity

import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.entity.models.Building
import com.flaringapp.coursework2021.data.repository.entity.models.Room

interface EntityRepository {

    suspend fun getBuildings(): CallResultList<Building>

    suspend fun addBuilding(building: Building): CallResultList<Building>
    suspend fun editBuilding(building: Building): CallResultList<Building>
    suspend fun deleteBuilding(id: String): CallResultNothing


    suspend fun getRooms(): CallResultList<Room>

    suspend fun addRoom(building: Room): CallResultList<Building>
    suspend fun editRoom(building: Room): CallResultList<Building>
    suspend fun deleteRoom(id: String): CallResultNothing

}