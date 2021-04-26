package com.flaringapp.coursework2021.data.network.features.profile

import com.flaringapp.coursework2021.data.network.base.ApiResponse
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse
import com.flaringapp.coursework2021.data.network.modifiers.annotations.WithoutApiKey
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProfileApiService {

    @POST("login")
    @FormUrlEncoded
    @WithoutApiKey
    suspend fun login(
        @Field("email") login: String,
        @Field("password") password: String
    ): ApiResponse<LoginResponse>

}