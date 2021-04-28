package com.flaringapp.coursework2021.data.network.features.managers

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.base.validateNoData
import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse

class ManagersSourceModelImpl(
    private val api: ManagersApiService
): ManagersSourceModel {

    override suspend fun getManagers(): CallResultList<ManagerResponse> {
        return api.getManagers()
            .validateList()
    }

    override suspend fun addManager(manager: ManagerRequest): CallResult<ManagerResponse> {
        return api.addManager(manager)
            .validate()
    }

    override suspend fun editManager(manager: ManagerRequest): CallResult<ManagerResponse> {
        return api.editManager(manager)
            .validate()
    }

    override suspend fun deleteManager(id: String): CallResultNothing {
        return api.deleteManager(id)
            .validateNoData()
    }
}