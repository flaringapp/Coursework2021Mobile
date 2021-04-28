package com.flaringapp.coursework2021.data.network.features.rents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.base.validateNoData
import com.flaringapp.coursework2021.data.network.features.rents.request.RentRequest
import com.flaringapp.coursework2021.data.network.features.rents.response.RentResponse

class RentsSourceModelImpl(
    private val api: RentsApiService
): RentsSourceModel {

    override suspend fun getRents(): CallResultList<RentResponse> {
        return api.getRents()
            .validateList()
    }

    override suspend fun getRents(roomId: String): CallResultList<RentResponse> {
        return api.getRents(roomId)
            .validateList()
    }

    override suspend fun addRent(rent: RentRequest): CallResult<RentResponse> {
        return api.addRent(rent)
            .validate()
    }

    override suspend fun editRent(rent: RentRequest): CallResult<RentResponse> {
        return api.editRent(rent)
            .validate()
    }

    override suspend fun deleteRent(id: String): CallResultNothing {
        return api.deleteRent(id)
            .validateNoData()
    }
}