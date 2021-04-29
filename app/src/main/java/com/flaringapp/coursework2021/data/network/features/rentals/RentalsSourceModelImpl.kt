package com.flaringapp.coursework2021.data.network.features.rentals

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.base.validateList
import com.flaringapp.coursework2021.data.network.base.validateNoData
import com.flaringapp.coursework2021.data.network.features.rentals.request.RentalRequest
import com.flaringapp.coursework2021.data.network.features.rentals.response.RentalResponse

class RentalsSourceModelImpl(
    private val api: RentalsApiService
): RentalsSourceModel {

    override suspend fun getRentals(): CallResultList<RentalResponse> {
        return api.getRentals()
            .validateList()
    }

    override suspend fun getRoomRentals(roomId: String): CallResultList<RentalResponse> {
        return api.getRoomRentals(roomId)
            .validateList()
    }

    override suspend fun getResidentRentals(residentId: String): CallResultList<RentalResponse> {
        return api.getResidentRentals()
            .validateList()
    }

    override suspend fun addRental(rental: RentalRequest): CallResult<RentalResponse> {
        return api.addRental(rental)
            .validate()
    }

    override suspend fun editRental(rental: RentalRequest): CallResult<RentalResponse> {
        return api.editRental(rental)
            .validate()
    }

    override suspend fun deleteRental(id: String): CallResultNothing {
        return api.deleteRental(id)
            .validateNoData()
    }
}