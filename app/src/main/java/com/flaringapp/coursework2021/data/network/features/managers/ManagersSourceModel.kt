package com.flaringapp.coursework2021.data.network.features.managers

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse
import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse

interface ManagersSourceModel {

    suspend fun getManagers(): CallResultList<ManagerResponse>

    suspend fun addManager(manager: ManagerRequest): CallResult<ManagerResponse>

    suspend fun editManager(manager: ManagerRequest): CallResult<ManagerResponse>

    suspend fun deleteManager(id: String): CallResultNothing

}