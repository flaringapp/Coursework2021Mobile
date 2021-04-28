package com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour

import com.flaringapp.coursework2021.data.repository.manager.ManagersRepository
import com.flaringapp.coursework2021.data.repository.manager.models.Manager
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerEditableData
import kotlinx.parcelize.IgnoredOnParcel
import org.koin.core.context.GlobalContext

abstract class BaseModifyManagerManagerBehaviour: ModifyManagerBehaviour {

    @IgnoredOnParcel
    protected val repository: ManagersRepository by GlobalContext.get().inject()

    @IgnoredOnParcel
    override val manager: Manager? = null

    @IgnoredOnParcel
    override val preliminaryData: ManagerEditableData? = null

}