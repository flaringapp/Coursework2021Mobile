package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.common.call.transformList
import com.flaringapp.coursework2021.data.network.features.rents.RentsSourceModel
import com.flaringapp.coursework2021.data.repository.tenants.models.Rent
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant

class RentsRepositoryImpl(
    private val sourceModel: RentsSourceModel
): RentsRepository {

    override suspend fun getRents(): CallResultList<Rent> {
        return sourceModel.getRents()
            .transformList { parseRent() }
    }

    override suspend fun getRents(roomId: String): CallResultList<Rent> {
        return sourceModel.getRents(roomId)
            .transformList { parseRent() }
    }

    override suspend fun addRent(roomId: String, tenant: Tenant): CallResult<Rent> {
        return sourceModel.addRent(Rent(Rent.NO_ID, roomId, tenant).asRequest())
            .transform { parseRent() }
    }

    override suspend fun removeRent(rentId: String): CallResultNothing {
        return sourceModel.deleteRent(rentId)
            .ignoreData()
    }
}