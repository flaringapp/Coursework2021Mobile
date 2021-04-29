package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.app.common.removeFirst
import com.flaringapp.coursework2021.app.common.replaceFirstOrAdd
import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.network.features.rentals.RentalsSourceModel
import com.flaringapp.coursework2021.data.network.features.rentals.request.RentalRequest
import com.flaringapp.coursework2021.data.network.features.rentals.response.RentalResponse

class RentalsSourceModelMock : RentalsSourceModel {

    private val rentals: MutableList<RentalResponse> = mutableListOf(
        RentalResponse("1", "1", "Room 1", 10000, "1", "Andrew", "Shpek"),
        RentalResponse("2", "2", "Room 2", 20000, "2","Gordon", "Watson"),
    )

    override suspend fun getRentals(): CallResultList<RentalResponse> {
        return CallResult.Success(rentals)
    }

    override suspend fun getRoomRentals(roomId: String): CallResultList<RentalResponse> {
        return CallResult.Success(rentals)
    }

    override suspend fun getResidentRentals(residentId: String): CallResultList<RentalResponse> {
        return CallResult.Success(rentals)
    }

    override suspend fun addRental(rental: RentalRequest): CallResult<RentalResponse> {
        val response = rental.toResponse()
        rentals += response
        return CallResult.Success(rental.toResponse())
    }

    override suspend fun editRental(rental: RentalRequest): CallResult<RentalResponse> {
        val response = rental.toResponse()
        rentals.replaceFirstOrAdd(response) { it.id == response.id }
        return CallResult.Success(rental.toResponse())
    }

    override suspend fun deleteRental(id: String): CallResultNothing {
        rentals.removeFirst { it.id == id }
        return CallResult.Nothing
    }

    private fun RentalRequest.toResponse() = RentalResponse(
        "10",
        roomId,
        if (roomId == "1") "Room 1" else "Room 2",
        if (roomId == "1") 10000 else 20000,
        residentId,
        if (residentId == "1") "Andrew" else "Gordon",
        if (residentId == "1") "Shpek" else "Watson"
    )
}