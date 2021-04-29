package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.common.call.transformList
import com.flaringapp.coursework2021.data.network.features.rentals.RentalsSourceModel
import com.flaringapp.coursework2021.data.repository.tenants.models.AddRental
import com.flaringapp.coursework2021.data.repository.tenants.models.Rental

class RentalsRepositoryImpl(
    private val sourceModel: RentalsSourceModel
): RentalsRepository {

    override suspend fun getRentals(): CallResultList<Rental> {
        return sourceModel.getRentals()
            .transformList { parseRent() }
    }

    override suspend fun getRentalsByRoom(roomId: String): CallResultList<Rental> {
        return sourceModel.getRoomRentals(roomId)
            .transformList { parseRent() }
    }

    override suspend fun getRentalsByResident(residentId: String): CallResultList<Rental> {
        return sourceModel.getResidentRentals(residentId)
            .transformList { parseRent() }
    }

    override suspend fun addRental(rental: AddRental): CallResult<Rental> {
        return sourceModel.addRental(rental.asRequest())
            .transform { parseRent() }
    }

    override suspend fun removeRental(rentId: String): CallResultNothing {
        return sourceModel.deleteRental(rentId)
            .ignoreData()
    }
}