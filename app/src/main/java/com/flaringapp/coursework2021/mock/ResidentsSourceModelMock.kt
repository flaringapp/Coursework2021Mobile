package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.replaceFirstOrAdd
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.residents.ResidentsSourceModel
import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse

class ResidentsSourceModelMock : ResidentsSourceModel {

    private val residents: MutableList<ResidentResponse> = mutableListOf(
        ResidentResponse("1", "Andrew", "Shpek", "ashpek@asd.com", null, "1", "B1"),
        ResidentResponse("2", "Gordon", "Watson", "gw@asd.com", "I am a cool guy", "2", "Cowi"),
    )

    override suspend fun getResidents(): CallResultList<ResidentResponse> {
        return CallResult.Success(residents)
    }

    override suspend fun getResidents(buildingId: String): CallResultList<ResidentResponse> {
        return CallResult.Success(residents)
    }

    override suspend fun addResident(resident: ResidentRequest): CallResult<ResidentResponse> {
        val response = resident.toResponse()
        residents += response
        return CallResult.Success(response)
    }

    override suspend fun editResident(resident: ResidentRequest): CallResult<ResidentResponse> {
        val response = resident.toResponse()
        residents.replaceFirstOrAdd(response) { it.id == response.id }
        return CallResult.Success(response)
    }

    override suspend fun deleteResident(id: String): CallResultNothing {
        residents.removeFirst { it.id == id }
        return CallResult.Nothing
    }

    private fun ResidentRequest.toResponse() = ResidentResponse(
        id,
        name,
        surname,
        email,
        description,
        buildingId,
        if (buildingId == "1") "B1"
        else "Cowi"
    )
}