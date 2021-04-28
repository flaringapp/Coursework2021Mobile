package com.flaringapp.coursework2021.data.network.features.buildings

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse

interface BuildingsSourceModel {

    suspend fun getBuildings(): CallResultList<BuildingsResponse>

    suspend fun addBuilding(building: BuildingRequest): CallResult<BuildingsResponse>

    suspend fun editBuilding(building: BuildingRequest): CallResult<BuildingsResponse>

    suspend fun deleteBuilding(id: String): CallResultNothing

}