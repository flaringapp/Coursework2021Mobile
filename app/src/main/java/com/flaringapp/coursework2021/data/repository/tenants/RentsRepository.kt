package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.tenants.models.Rent
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant

interface RentsRepository {

    suspend fun getRents(): CallResultList<Rent>

    suspend fun getRents(roomId: String): CallResultList<Rent>

    suspend fun addRent(roomId: String, tenant: Tenant): CallResult<Rent>

    suspend fun removeRent(rentId: String): CallResultNothing

}