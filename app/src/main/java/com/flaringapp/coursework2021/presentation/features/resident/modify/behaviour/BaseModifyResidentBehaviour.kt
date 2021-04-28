package com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour

import com.flaringapp.coursework2021.data.repository.residents.ResidentsRepository
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.presentation.features.resident.modify.models.ResidentEditableData
import kotlinx.parcelize.IgnoredOnParcel
import org.koin.core.context.GlobalContext

abstract class BaseModifyResidentBehaviour : ModifyResidentBehaviour {

    @IgnoredOnParcel
    protected val repository: ResidentsRepository by GlobalContext.get().inject()

    @IgnoredOnParcel
    override val resident: Resident? = null

    @IgnoredOnParcel
    override val preliminaryData: ResidentEditableData? = null

}