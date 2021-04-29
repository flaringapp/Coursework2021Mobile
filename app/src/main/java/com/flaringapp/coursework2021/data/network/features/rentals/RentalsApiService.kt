package com.flaringapp.coursework2021.data.network.features.rentals

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.rentals.request.RentalRequest
import com.flaringapp.coursework2021.data.network.features.rentals.response.RentalResponse
import retrofit2.http.*

interface RentalsApiService {

    @GET("rentals")
    fun getRentals(
        @Query("room_id") roomId: String? = null
    ): ApiResponseList<RentalResponse>

    @GET("rentals")
    fun getRoomRentals(
        @Query("room_id") roomId: String? = null
    ): ApiResponseList<RentalResponse>

    @GET("rentals")
    fun getResidentRentals(
        @Query("resident_id") residentId: String? = null
    ): ApiResponseList<RentalResponse>

    @PUT("rental")
    fun addRental(
        @Body rental: RentalRequest
    ): ApiResponse<RentalResponse>

    @POST("rental")
    fun editRental(
        @Body rental: RentalRequest
    ): ApiResponse<RentalResponse>

    @DELETE("rental")
    @FormUrlEncoded
    fun deleteRental(
        @Field("id") id: String
    ): ApiResponseSuccess

}