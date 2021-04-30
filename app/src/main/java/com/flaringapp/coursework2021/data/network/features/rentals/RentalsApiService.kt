package com.flaringapp.coursework2021.data.network.features.rentals

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.rentals.request.RentalRequest
import com.flaringapp.coursework2021.data.network.features.rentals.response.RentalResponse
import retrofit2.http.*

interface RentalsApiService {

    @GET("rentals")
    suspend fun getRentals(): ApiResponseList<RentalResponse>

    @GET("rentals")
    suspend fun getRoomRentals(
        @Query("roomId") roomId: String? = null
    ): ApiResponseList<RentalResponse>

    @GET("rentals")
    suspend fun getResidentRentals(
        @Query("residentId") residentId: String? = null
    ): ApiResponseList<RentalResponse>

    @PUT("rental")
    suspend fun addRental(
        @Body rental: RentalRequest
    ): ApiResponse<RentalResponse>

    @POST("rental")
    suspend fun editRental(
        @Body rental: RentalRequest
    ): ApiResponse<RentalResponse>

    @DELETE("rental")
    suspend fun deleteRental(
        @Query("id") id: String
    ): ApiResponseSuccess

}