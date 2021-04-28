package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.replaceFirstOrAdd
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.rents.RentsSourceModel
import com.flaringapp.coursework2021.data.network.features.rents.request.RentRequest
import com.flaringapp.coursework2021.data.network.features.rents.response.RentResponse

class RentsSourceModelMock : RentsSourceModel {

    private val rents: MutableList<RentResponse> = mutableListOf(
        RentResponse("1", "1", "1", "Andrew", "Shpek"),
        RentResponse("2", "2", "1", "Andrew", "Shpek"),
    )

    override suspend fun getRents(): CallResultList<RentResponse> {
        return CallResult.Success(rents)
    }

    override suspend fun getRents(roomId: String): CallResultList<RentResponse> {
        return CallResult.Success(rents)
    }

    override suspend fun addRent(rent: RentRequest): CallResult<RentResponse> {
        val response = rent.toResponse()
        rents += response
        return CallResult.Success(rent.toResponse())
    }

    override suspend fun editRent(rent: RentRequest): CallResult<RentResponse> {
        val response = rent.toResponse()
        rents.replaceFirstOrAdd(response) { it.id == response.id }
        return CallResult.Success(rent.toResponse())
    }

    override suspend fun deleteRent(id: String): CallResultNothing {
        rents.removeFirst { it.id == id }
        return CallResult.Nothing
    }

    private fun RentRequest.toResponse() = RentResponse(
        id,
        roomId,
        residentId,
        "Andrew",
        "Shpek"
    )
}