package com.flaringapp.coursework2021.data.network.features.profile

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.network.base.validate
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse

class ProfileSourceModelImpl(
    private val api: ProfileApiService
): ProfileSourceModel {

    override suspend fun login(login: String, password: String): CallResult<LoginResponse> {
        return api.login(login, password)
            .validate()
    }

}