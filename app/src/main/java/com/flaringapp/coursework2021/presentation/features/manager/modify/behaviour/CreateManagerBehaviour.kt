package com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateManagerBehaviour: BaseModifyManagerManagerBehaviour() {

    override suspend fun modifyManager(manager: Manager): CallResultNothing {
        return repository.addManager(manager)
            .ignoreData()
    }
}