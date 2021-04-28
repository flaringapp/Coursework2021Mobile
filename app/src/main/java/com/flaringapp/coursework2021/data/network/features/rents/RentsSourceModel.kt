package com.flaringapp.coursework2021.data.network.features.rents

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.rents.request.RentRequest
import com.flaringapp.coursework2021.data.network.features.rents.response.RentResponse
import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse

interface RentsSourceModel {

    suspend fun getRents(): CallResultList<RentResponse>
    suspend fun getRents(roomId: String): CallResultList<RentResponse>

    suspend fun addRent(rent: RentRequest): CallResult<RentResponse>

    suspend fun editRent(rent: RentRequest): CallResult<RentResponse>

    suspend fun deleteRent(id: String): CallResultNothing

}