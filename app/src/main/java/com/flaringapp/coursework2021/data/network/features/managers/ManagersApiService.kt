package com.flaringapp.coursework2021.data.network.features.managers

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomRequest
import com.flaringapp.coursework2021.data.network.features.rooms.response.RoomResponse
import retrofit2.http.*

interface ManagersApiService {

    @GET("managers")
    fun getManagers(): ApiResponseList<ManagerResponse>

    @PUT("manager")
    fun addManager(
        @Body manager: ManagerRequest
    ): ApiResponse<ManagerResponse>

    @POST("manager")
    fun editManager(
        @Body manager: ManagerRequest
    ): ApiResponse<ManagerResponse>

    @DELETE("manager")
    @FormUrlEncoded
    fun deleteManager(
        @Field("id") id: String
    ): ApiResponseSuccess

}