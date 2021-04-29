package com.flaringapp.coursework2021.data.repository.tenants

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.common.call.CallResultList
import com.flaringapp.coursework2021.data.common.call.CallResultNothing
import com.flaringapp.coursework2021.data.repository.tenants.models.AddRental
import com.flaringapp.coursework2021.data.repository.tenants.models.Rental
import com.flaringapp.coursework2021.data.repository.tenants.models.Tenant

interface RentalsRepository {

    suspend fun getRentals(): CallResultList<Rental>

    suspend fun getRentalsByRoom(roomId: String): CallResultList<Rental>

    suspend fun getRentalsByResident(residentId: String): CallResultList<Rental>

    suspend fun addRental(rental: AddRental): CallResult<Rental>

    suspend fun removeRental(rentId: String): CallResultNothing

}