package com.flaringapp.coursework2021.mock

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.request.LoginRequest
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse

class ProfileSourceModelMock : ProfileSourceModel {

    override suspend fun login(request: LoginRequest): CallResult<LoginResponse> {
        return CallResult.Success(
            LoginResponse(
                "1",
                "Name",
                "Surname",
                "namesurname@gmail.com",
                "token_namesurname",
                "manager",
                "1"
            )
        )
    }
}