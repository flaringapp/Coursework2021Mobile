package com.flaringapp.coursework2021.data.network.features.residents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.base.validateNoData
import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse

class ResidentsSourceModelImpl(
    private val api: ResidentsApiService
): ResidentsSourceModel {

    override suspend fun getResidents(): CallResultList<ResidentResponse> {
        return api.getResidents()
            .validateList()
    }

    override suspend fun getResidents(buildingId: String): CallResultList<ResidentResponse> {
        return api.getResidents(buildingId)
            .validateList()
    }

    override suspend fun addResident(resident: ResidentRequest): CallResult<ResidentResponse> {
        return api.addResident(resident)
            .validate()
    }

    override suspend fun editResident(resident: ResidentRequest): CallResult<ResidentResponse> {
        return api.editResident(resident)
            .validate()
    }

    override suspend fun deleteResident(id: String): CallResultNothing {
        return api.deleteResident(id)
            .validateNoData()
    }
}