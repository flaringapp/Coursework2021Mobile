package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.replaceFirstOrAdd
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.managers.ManagersSourceModel
import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse

class ManagersSourceModelMock : ManagersSourceModel {

    private val managers: MutableList<ManagerResponse> = mutableListOf(
        ManagerResponse("1", "Andrew", "Shpek", "ashpek@asd.com", null, "1", "B1"),
        ManagerResponse("2", "Gordon", "Watson", "gw@asd.com", "I am a cool guy", "2", "Cowi"),
    )

    override suspend fun getManagers(): CallResultList<ManagerResponse> {
        return CallResult.Success(managers)
    }

    override suspend fun addManager(manager: ManagerRequest): CallResult<ManagerResponse> {
        val response = manager.toResponse()
        managers += response
        return CallResult.Success(response)
    }

    override suspend fun editManager(manager: ManagerRequest): CallResult<ManagerResponse> {
        val response = manager.toResponse()
        managers.replaceFirstOrAdd(response) { it.id == response.id }
        return CallResult.Success(response)
    }

    override suspend fun deleteManager(id: String): CallResultNothing {
        managers.removeFirst { it.id == id }
        return CallResult.Nothing
    }

    private fun ManagerRequest.toResponse() = ManagerResponse(
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