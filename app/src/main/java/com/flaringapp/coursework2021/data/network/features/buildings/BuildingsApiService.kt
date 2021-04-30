package com.flaringapp.coursework2021.data.network.features.buildings

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.buildings.request.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse
import retrofit2.http.*

interface BuildingsApiService {

    @GET("locations")
    suspend fun getBuildings(): ApiResponseList<BuildingsResponse>

    @PUT("location")
    suspend fun addBuilding(
        @Body building: BuildingRequest
    ): ApiResponse<BuildingsResponse>

    @POST("location")
    suspend fun editBuilding(
        @Body building: BuildingRequest
    ): ApiResponse<BuildingsResponse>

    @DELETE("location")
    suspend fun deleteBuilding(
        @Query("id") id: String
    ): ApiResponseSuccess

}