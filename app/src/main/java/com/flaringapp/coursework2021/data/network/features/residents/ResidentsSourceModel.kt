package com.flaringapp.coursework2021.data.network.features.residents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse

interface ResidentsSourceModel {

    suspend fun getResidents(): CallResultList<ResidentResponse>
    suspend fun getResidents(buildingId: String): CallResultList<ResidentResponse>

    suspend fun addResident(resident: ResidentRequest): CallResult<ResidentResponse>

    suspend fun editResident(resident: ResidentRequest): CallResult<ResidentResponse>

    suspend fun deleteResident(id: String): CallResultNothing

}