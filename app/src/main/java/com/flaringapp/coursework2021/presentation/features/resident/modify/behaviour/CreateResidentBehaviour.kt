package com.flaringapp.coursework2021.presentation.features.resident.modify.behaviour

import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import kotlinx.parcelize.Parcelize

@Parcelize
class CreateResidentBehaviour : BaseModifyResidentBehaviour() {

    override suspend fun modifyResident(resident: Resident): CallResultNothing {
        return repository.addResident(resident)
            .ignoreData()
    }
}