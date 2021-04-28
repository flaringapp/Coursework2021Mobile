package com.flaringapp.coursework2021.data.repository.managers

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.common.call.transformList
import com.flaringapp.coursework2021.data.network.features.managers.ManagersSourceModel
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import com.flaringapp.coursework2021.data.repository.managers.storage.ManagersStorage
import kotlinx.coroutines.flow.MutableSharedFlow

class ManagersRepositoryImpl(
    private val sourceModel: ManagersSourceModel
) : ManagersRepository, ManagersStorage {

    override val addManagerFlow = MutableSharedFlow<Manager>()
    override val editManagerFlow = MutableSharedFlow<Manager>()
    override val deleteManagerFlow = MutableSharedFlow<String>()

    override suspend fun getManagers(): CallResultList<Manager> {
        return sourceModel.getManagers()
            .transformList { parseManager() }
    }

    override suspend fun addManager(manager: Manager): CallResult<Manager> {
        return sourceModel.addManager(manager.asRequest())
            .transform { parseManager() }
            .doOnSuccessSuspend { addManagerFlow.emit(it) }
    }

    override suspend fun editManager(manager: Manager): CallResult<Manager> {
        return sourceModel.editManager(manager.asRequest())
            .transform { parseManager() }
            .doOnSuccessSuspend { editManagerFlow.emit(it) }
    }

    override suspend fun deleteManager(id: String): CallResultNothing {
        return sourceModel.deleteManager(id)
            .doOnSuccessSuspend { deleteManagerFlow.emit(id) }
    }
}