package com.flaringapp.coursework2021.data.repository.managers

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.managers.models.Manager

interface ManagersRepository {

    suspend fun getManagers(): CallResultList<Manager>

    suspend fun addManager(manager: Manager): CallResult<Manager>

    suspend fun editManager(manager: Manager): CallResult<Manager>

    suspend fun deleteManager(id: String): CallResultNothing

}