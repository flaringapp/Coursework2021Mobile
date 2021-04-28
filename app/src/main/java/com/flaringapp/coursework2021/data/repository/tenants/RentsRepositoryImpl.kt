package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.tenants.models.Rent
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant

class RentsRepositoryImpl: RentsRepository {

    override suspend fun getRents(roomId: String): CallResultList<Rent> {
        return CallResult.Success(emptyList())
    }

    override suspend fun addRent(roomId: String, tenant: Tenant): CallResult<Rent> {
        return CallResult.Success(Rent("1", roomId, tenant))
    }

    override suspend fun removeRent(rentId: String): CallResultNothing {
        return CallResult.Nothing
    }
}