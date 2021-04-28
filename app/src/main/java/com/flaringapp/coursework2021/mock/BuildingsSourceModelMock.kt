package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.replaceFirstOrAdd
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.buildings.BuildingsSourceModel
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse

class BuildingsSourceModelMock : BuildingsSourceModel {

    private val buildings: MutableList<BuildingsResponse> = mutableListOf(
        BuildingsResponse("1", "B1", "Description", null, null, "Schevchenka str.", 412.4f),
        BuildingsResponse("2", "Cowi", null, -41.2, 23.3, "22 Kobzy str.", 512.2f),
    )

    override suspend fun getBuildings(): CallResultList<BuildingsResponse> {
        return CallResult.Success(buildings)
    }

    override suspend fun addBuilding(building: BuildingRequest): CallResult<BuildingsResponse> {
        val response = building.toResponse()
        buildings += response
        return CallResult.Success(response)
    }

    override suspend fun editBuilding(building: BuildingRequest): CallResult<BuildingsResponse> {
        val response = building.toResponse()
        buildings.replaceFirstOrAdd(response) { it.id == response.id }
        return CallResult.Success(response)
    }

    override suspend fun deleteBuilding(id: String): CallResultNothing {
        buildings.removeFirst { it.id == id }
        return CallResult.Nothing
    }

    private fun BuildingRequest.toResponse() = BuildingsResponse(
        id, name, description, lat, lon, address, area
    )
}