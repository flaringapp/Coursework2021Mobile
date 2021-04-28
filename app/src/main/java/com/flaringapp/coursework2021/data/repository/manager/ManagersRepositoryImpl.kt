package com.flaringapp.coursework2021.data.repository.manager

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.manager.models.Manager
import com.flaringapp.coursework2021.data.repository.manager.storage.ManagersStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ManagersRepositoryImpl : ManagersRepository, ManagersStorage {

    override val addManagerFlow: Flow<Manager> = MutableSharedFlow()
    override val editManagerFlow: Flow<Manager> = MutableSharedFlow()
    override val deleteManagerFlow: Flow<String> = MutableSharedFlow()

    override suspend fun getManagers(): CallResultList<Manager> {
        return CallResult.Success(listOf(
            Manager("1", "Andrew", "Shpek", "as@asd.com", "", "1", "Cowi 1"),
            Manager("2", "Boda", "asd", "as@asd.com", "", "1", "Cowi 1"),
        ))
    }

    override suspend fun addManager(manager: Manager): CallResult<Manager> {
        return CallResult.Success(manager)
    }

    override suspend fun editManager(manager: Manager): CallResult<Manager> {
        return CallResult.Success(manager)
    }

    override suspend fun deleteManager(id: String): CallResultNothing {
        return CallResult.Nothing
    }
}