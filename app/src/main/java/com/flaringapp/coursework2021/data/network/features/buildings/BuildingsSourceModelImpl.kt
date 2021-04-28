package com.flaringapp.coursework2021.data.network.features.buildings

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.base.validateNoData
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse

class BuildingsSourceModelImpl(
    private val api: BuildingsApiService
): BuildingsSourceModel {

    override suspend fun getBuildings(): CallResultList<BuildingsResponse> {
        return api.getBuildings()
            .validateList()
    }

    override suspend fun addBuilding(building: BuildingRequest): CallResult<BuildingsResponse> {
        return api.addBuilding(building)
            .validate()
    }

    override suspend fun editBuilding(building: BuildingRequest): CallResult<BuildingsResponse> {
        return api.editBuilding(building)
            .validate()
    }

    override suspend fun deleteBuilding(id: String): CallResultNothing {
        return api.deleteBuilding(id)
            .validateNoData()
    }
}