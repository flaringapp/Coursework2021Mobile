package com.flaringapp.coursework2021.data.repository.profile

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.repository.profile.models.LoginData
import com.flaringapp.coursework2021.data.repository.profile.models.Profile

interface ProfileRepository {

    suspend fun login(login: String, password: String): CallResult<LoginData>

    fun getCurrentProfile(): Profile?

}