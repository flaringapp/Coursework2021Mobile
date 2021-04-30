package com.flaringapp.coursework2021.data.network.features.rooms

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.rooms.request.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse
import retrofit2.http.*

interface RoomsApiService {

    @GET("rooms")
    suspend fun getRooms(
        @Query("locationId") buildingId: String? = null
    ): ApiResponseList<RoomResponse>

    @PUT("room")
    suspend fun addRoom(
        @Body room: RoomRequest
    ): ApiResponse<RoomResponse>

    @POST("room")
    suspend fun editRoom(
        @Body room: RoomRequest
    ): ApiResponse<RoomResponse>

    @DELETE("room")
    suspend fun deleteRoom(
        @Query("id") id: String
    ): ApiResponseSuccess

}