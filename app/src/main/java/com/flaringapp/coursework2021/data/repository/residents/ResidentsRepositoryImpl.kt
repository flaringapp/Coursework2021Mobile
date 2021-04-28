package com.flaringapp.coursework2021.data.repository.residents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.common.call.transformList
import com.flaringapp.coursework2021.data.network.features.residents.ResidentsSourceModel
import com.flaringapp.coursework2021.data.repository.residents.models.Resident
import com.flaringapp.coursework2021.data.repository.residents.storage.ResidentsStorage
import kotlinx.coroutines.flow.MutableSharedFlow

class ResidentsRepositoryImpl(
    private val sourceModel: ResidentsSourceModel
): ResidentsRepository, ResidentsStorage {

    override val addResidentFlow = MutableSharedFlow<Resident>()
    override val editResidentFlow = MutableSharedFlow<Resident>()
    override val deleteResidentFlow = MutableSharedFlow<String>()

    override suspend fun getResidents(): CallResultList<Resident> {
        return sourceModel.getResidents()
            .transformList { parseResident() }
    }

    override suspend fun getResidents(buildingId: String): CallResultList<Resident> {
        return sourceModel.getResidents()
            .transformList { parseResident() }
    }

    override suspend fun addResident(resident: Resident): CallResult<Resident> {
        return sourceModel.addResident(resident.asRequest())
            .transform { parseResident() }
            .doOnSuccessSuspend { addResidentFlow.emit(resident) }
    }

    override suspend fun editResident(resident: Resident): CallResult<Resident> {
        return sourceModel.editResident(resident.asRequest())
            .transform { parseResident() }
            .doOnSuccessSuspend { editResidentFlow.emit(resident) }
    }

    override suspend fun deleteResident(id: String): CallResultNothing {
        return sourceModel.deleteResident(id)
            .doOnSuccessSuspend { deleteResidentFlow.emit(id) }
    }

}