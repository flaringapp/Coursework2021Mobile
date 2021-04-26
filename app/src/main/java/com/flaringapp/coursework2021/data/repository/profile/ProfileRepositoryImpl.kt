package com.flaringapp.coursework2021.data.repository.profile

import com.flaringapp.coursework2021.data.common.call.CallResult
import com.flaringapp.coursework2021.data.network.features.profile.ProfileSourceModel
import com.flaringapp.coursework2021.data.network.features.profile.response.LoginResponse
import com.flaringapp.coursework2021.data.repository.profile.models.LoginData
import com.flaringapp.coursework2021.data.repository.profile.models.Profile
import com.flaringapp.coursework2021.data.storage.DataStorage

class ProfileRepositoryImpl(
    private val sourceModel: ProfileSourceModel,
    private val dataStorage: DataStorage
) : ProfileRepository {

    private var currentProfile: Profile? = null

    override suspend fun login(login: String, password: String): CallResult<LoginData> {
        return sourceModel.login(login, password)
            .transform { parseLoginResponse() }
            .doOnSuccess {
                currentProfile = it.profile
                dataStorage.token = it.token
                dataStorage.userId = it.profile.id
            }
    }

    override fun getCurrentProfile(): Profile? {
        return currentProfile
    }

    private fun LoginResponse.parseLoginResponse(): LoginData {
        return LoginData(
            Profile(id, name, surname, email),
            token
        )
    }
}