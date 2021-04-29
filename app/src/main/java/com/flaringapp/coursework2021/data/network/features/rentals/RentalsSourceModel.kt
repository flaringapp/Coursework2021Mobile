package com.flaringapp.coursework2021.data.network.features.rentals

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.rentals.request.RentalRequest
import com.flaringapp.coursework2021.data.network.features.rentals.response.RentalResponse

interface RentalsSourceModel {

    suspend fun getRentals(): CallResultList<RentalResponse>
    suspend fun getRoomRentals(roomId: String): CallResultList<RentalResponse>
    suspend fun getResidentRentals(residentId: String): CallResultList<RentalResponse>

    suspend fun addRental(rental: RentalRequest): CallResult<RentalResponse>

    suspend fun editRental(rental: RentalRequest): CallResult<RentalResponse>

    suspend fun deleteRental(id: String): CallResultNothing

}