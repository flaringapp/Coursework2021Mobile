package com.flaringapp.coursework2021.data.repository.residents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.residents.models.Resident

interface ResidentsRepository {

    suspend fun getResidents(): CallResultList<Resident>
    suspend fun getResidents(buildingId: String): CallResultList<Resident>

    suspend fun addResident(resident: Resident): CallResult<Resident>

    suspend fun editResident(resident: Resident): CallResult<Resident>

    suspend fun deleteResident(id: String): CallResultNothing


}