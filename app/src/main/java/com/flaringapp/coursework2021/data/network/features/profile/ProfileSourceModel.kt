package com.flaringapp.coursework2021.data.network.features.profile

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse

interface ProfileSourceModel {

    suspend fun login(login: String, password: String): CallResult<LoginResponse>

}