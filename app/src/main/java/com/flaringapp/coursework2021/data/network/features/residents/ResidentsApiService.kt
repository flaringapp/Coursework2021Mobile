package com.flaringapp.coursework2021.data.network.features.residents

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.base.ApiResponseList
import com.flaringapp.coursework2021.data.network.base.ApiResponseSuccess
import com.flaringapp.coursework2021.data.network.features.residents.request.ResidentRequest
import com.flaringapp.coursework2021.data.network.features.residents.response.ResidentResponse
import retrofit2.http.*

interface ResidentsApiService {

    @GET("residents")
    fun getResidents(
        @Query("building_id") buildingId: String? = null
    ): ApiResponseList<ResidentResponse>

    @PUT("resident")
    fun addResident(
        @Body resident: ResidentRequest
    ): ApiResponse<ResidentResponse>

    @POST("resident")
    fun editResident(
        @Body resident: ResidentRequest
    ): ApiResponse<ResidentResponse>

    @DELETE("resident")
    @FormUrlEncoded
    fun deleteResident(
        @Query("id") id: String
    ): ApiResponseSuccess

}