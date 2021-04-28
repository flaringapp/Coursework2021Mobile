package com.flaringapp.coursework2021.data.network.features.buildings

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingRequest
import com.flaringapp.coursework2021.data.network.features.buildings.response.BuildingsResponse
import retrofit2.http.*

interface BuildingsApiService {

    @GET("buildings")
    fun getBuildings(): ApiResponseList<BuildingsResponse>

    @PUT("building")
    fun addBuilding(
        @Body building: BuildingRequest
    ): ApiResponse<BuildingsResponse>

    @POST("building")
    fun editBuilding(
        @Body building: BuildingRequest
    ): ApiResponse<BuildingsResponse>

    @DELETE
    @FormUrlEncoded
    fun deleteBuilding(
        @Field("id") id: String
    ): ApiResponseSuccess

}