package com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.presentation.features.resident.modify.models.ResidentEditableData
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class EditResidentBehaviour(
    override val resident: Resident
) : BaseModifyResidentBehaviour() {

    @IgnoredOnParcel
    override val preliminaryData: ResidentEditableData
        get() = ResidentEditableData(resident)

    override suspend fun modifyResident(resident: Resident): CallResultNothing {
        return repository.editResident(resident)
            .ignoreData()
    }
}