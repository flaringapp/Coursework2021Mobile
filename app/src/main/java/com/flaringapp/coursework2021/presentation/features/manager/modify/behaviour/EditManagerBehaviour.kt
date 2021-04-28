package com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerEditableData
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class EditManagerBehaviour(
    override val manager: Manager
): BaseModifyManagerBehaviour() {

    @IgnoredOnParcel
    override val preliminaryData: ManagerEditableData
        get() = ManagerEditableData(manager)

    override suspend fun modifyManager(manager: Manager): CallResultNothing {
        return repository.editManager(manager)
            .ignoreData()
    }
}