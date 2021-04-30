package com.flaringapp.coursework2021.data.network.features.profile

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.features.profile.request.LoginRequest
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse
import com.flaringapp.coursework2021.data.network.modifiers.annotations.WithoutApiKey
import retrofit2.http.*

interface ProfileApiService {

    @POST("login")
    @WithoutApiKey
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResponse<LoginResponse>

}