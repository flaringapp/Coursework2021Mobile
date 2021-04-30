package com.flaringapp.coursework2021.presentation.features.manager.modify.behaviour

import com.flaringapp.coursework2021.data.repository.managers.ManagersRepository
import com.flaringapp.coursework2021.data.repository.managers.models.Manager
import com.flaringapp.coursework2021.presentation.features.manager.modify.models.ManagerEditableData
import kotlinx.parcelize.IgnoredOnParcel
import org.koin.core.context.GlobalContext

abstract class BaseModifyManagerBehaviour: ModifyManagerBehaviour {

    @IgnoredOnParcel
    protected val repository: ManagersRepository by GlobalContext.get().inject()

    @IgnoredOnParcel
    override val manager: Manager? = null

    @IgnoredOnParcel
    override val preliminaryData: ManagerEditableData? = null

    @IgnoredOnParcel
    override val isPasswordRequired: Boolean = false

}