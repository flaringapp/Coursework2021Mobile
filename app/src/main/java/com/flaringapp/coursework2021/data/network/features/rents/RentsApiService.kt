package com.flaringapp.coursework2021.data.network.features.rents

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse
import com.flaringapp.coursework2021.data.network.features.rents.request.RentRequest
import com.flaringapp.coursework2021.data.network.features.rents.response.RentResponse
import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse
import retrofit2.http.*

interface RentsApiService {

    @GET("rents")
    fun getRents(
        @Query("room_id") roomId: String? = null
    ): ApiResponseList<RentResponse>

    @PUT("rent")
    fun addRent(
        @Body rent: RentRequest
    ): ApiResponse<RentResponse>

    @POST("rent")
    fun editRent(
        @Body rent: RentRequest
    ): ApiResponse<RentResponse>

    @DELETE("rent")
    @FormUrlEncoded
    fun deleteRent(
        @Field("id") id: String
    ): ApiResponseSuccess

}