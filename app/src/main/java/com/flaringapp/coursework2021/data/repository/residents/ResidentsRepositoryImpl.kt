package com.flaringapp.coursework2021.data.repository.residents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.residents.storage.ResidentsStorage
import kotlinx.coroutines.flow.MutableSharedFlow

class ResidentsRepositoryImpl: ResidentsRepository, ResidentsStorage {

    override val addResidentFlow = MutableSharedFlow<Resident>()
    override val editResidentFlow = MutableSharedFlow<Resident>()
    override val deleteResidentFlow = MutableSharedFlow<String>()

    override suspend fun getResidents(): CallResultList<Resident> {
        return CallResult.Success(listOf(
            Resident("1", "Andrew", "Shpek", "as@asdc.com", "", "1", "B1")
        ))
    }

    override suspend fun getResidents(buildingId: String): CallResultList<Resident> {
        return CallResult.Success(listOf(
            Resident("1", "Andrew", "Shpek", "as@asdc.com", "", "1", "B1")
        ))
    }

    override suspend fun addResident(resident: Resident): CallResult<Resident> {
        addResidentFlow.emit(resident)
        return CallResult.Success(resident)
    }

    override suspend fun editResident(resident: Resident): CallResult<Resident> {
        editResidentFlow.emit(resident)
        return CallResult.Success(resident)
    }

    override suspend fun deleteResident(id: String): CallResultNothing {
        deleteResidentFlow.emit(id)
        return CallResult.Nothing
    }
}