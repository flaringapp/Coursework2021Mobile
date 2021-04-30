package com.flaringapp.coursework2021.data.network.features.managers

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.managers.request.ManagerRequest
import com.flaringapp.coursework2021.data.network.features.managers.response.ManagerResponse
import retrofit2.http.*

interface ManagersApiService {

    @GET("managers")
    suspend fun getManagers(): ApiResponseList<ManagerResponse>

    @PUT("manager")
    suspend fun addManager(
        @Body manager: ManagerRequest
    ): ApiResponse<ManagerResponse>

    @POST("manager")
    suspend fun editManager(
        @Body manager: ManagerRequest
    ): ApiResponse<ManagerResponse>

    @DELETE("manager")
    suspend fun deleteManager(
        @Query("id") id: String
    ): ApiResponseSuccess

}