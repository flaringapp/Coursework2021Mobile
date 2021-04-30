package com.flaringapp.coursework2021.data.network.features.profile

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.network.features.profile.request.LoginRequest
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse

interface ProfileSourceModel {

    suspend fun login(request: LoginRequest): CallResult<LoginResponse>

}